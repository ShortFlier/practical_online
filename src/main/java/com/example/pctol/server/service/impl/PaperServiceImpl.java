package com.example.pctol.server.service.impl;

import com.example.pctol.common.constant.MsgConstant;
import com.example.pctol.common.constant.PaperConstant;
import com.example.pctol.common.constant.StateCode;
import com.example.pctol.common.constant.TopicConstant;
import com.example.pctol.common.utils.GradeCompute;
import com.example.pctol.common.utils.Util;
import com.example.pctol.pojo.DTO.PaperSearchDTO;
import com.example.pctol.pojo.DTO.SmtAswListDTO;
import com.example.pctol.pojo.DTO.SmtPaperDTO;
import com.example.pctol.pojo.DTO.TestRdmDTO;
import com.example.pctol.pojo.VO.*;
import com.example.pctol.pojo.entity.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.pctol.server.mapper.PaperMapper;
import com.example.pctol.server.mapper.SubjectMapper;
import com.example.pctol.server.mapper.TestMapper;
import com.example.pctol.server.mapper.TopicPublic;
import com.example.pctol.server.service.PaperService;
import com.example.pctol.server.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.pctol.common.constant.TopicConstant.TOPIC_TYPE_ARRAY;

/**
 * @author hp
 * @date 2024/4/15
 */
@Service
@Slf4j
public class PaperServiceImpl implements PaperService {
    @Autowired
    private PaperMapper paperMapper;
    @Autowired
    private SubjectMapper subjectMapper;
    @Autowired
    private TopicService topicService;
    @Autowired
    private TestMapper testMapper;

    @Override
    public Result gets(PaperSearchDTO paperSearchDTO) {
        int total=paperMapper.count(paperSearchDTO);
        if(total==0)
            return new Result(StateCode.NOT_DATA,MsgConstant.NO_DATA);
        List<Paper> paperList=paperMapper.gets(paperSearchDTO, paperSearchDTO.getStart(), paperSearchDTO.getPageSize());
        //封装成PaperVO数组返回
        List<PaperVO> paperVOList=new ArrayList<>();
        //获取SubVO数组
        List<SubVO> subVOList=subjectMapper.getList();
        for (Paper paper: paperList) {
            //根据paper.subjectId获取subjectName
            String subjectName= Util.getSubjectName(paper.getSubjectId(),subVOList);
            paperVOList.add(new PaperVO(paper,subjectName));
        }
        PageResult pageResult=new PageResult(total,paperVOList);
        return Result.success(pageResult);
    }

    @Override
    public Result getByIdNt(long id,boolean eraseAnswer) {
//        获取Paper
        Paper paper=paperMapper.getPaper(id);
//        获取PaperDetail
        PaperDetail paperDetail=paperMapper.getPaperDetail(id);
        PaperDetailVO paperDetailVO=new PaperDetailVO(paper,paperDetail);
        //填充paperDetailVO中的list字段
        topicListFill(paperDetail,paperDetailVO,eraseAnswer);
        return Result.success(paperDetailVO);
    }

    @Override
    public Result test(long id) {
        Result result=getByIdNt(id,false);
        PaperDetailVO paperDetailVO= (PaperDetailVO) result.getData();
        //将paperDetailVo的list中填空，应用题的答案answer以特殊字符替换
        if(paperDetailVO.getFitbList()!=null&&paperDetailVO.getFitbList().size()>0){
            for (Topic fillInTheBlank : paperDetailVO.getFitbList()) {
                String answer= fillInTheBlank.getAnswer();
                fillInTheBlank.setAnswer(Util.hideAnswer(answer));
            }
        }
        if(paperDetailVO.getVocList()!=null&&paperDetailVO.getVocList().size()>0){
            for (Topic vocalQst : paperDetailVO.getVocList()) {
                String answer= vocalQst.getAnswer();
                vocalQst.setAnswer(Util.hideAnswer(answer));
            }
        }
        return Result.success(paperDetailVO);
    }

    //交卷
    @Override
    public void testSubmit(SmtPaperDTO smtPaperDTO) {
        //保存到test表
        Test test=new Test(smtPaperDTO);
        testMapper.insert(test);
        //保存paper_topic表
        Long id=test.getId();
        List<PaperTopic> paperTopics=new ArrayList<>();
        //装填list，给选择，判断打分
        //装填
        for (Object value: smtPaperDTO.getSmtAswListDTOList()) {
            paperTopics.add(new PaperTopic(smtPaperDTO).setTopic((SmtAswListDTO) value).setTestId(id));
        }
        //打分
        for (PaperTopic paperTopic: paperTopics) {
            //打分选择和判断
            if(paperTopic.getType()>=TopicConstant.RADIOES&&paperTopic.getType()<=TopicConstant.JUDGMENT){
                TopicPublic topicMapper= topicService.getTopicMapper(paperTopic.getType());
                Topic topic=topicMapper.getByIds(Collections.singletonList(paperTopic.getTopicId())).get(0);
                int[] maskArr=getMask(paperTopic.getPaperId());
                if(paperTopic.getType()==TopicConstant.MULTIPLE_CHOICES){ //多选
                    GradeCompute.mulChoGrade(paperTopic, (MultipleChoices) topic);
                    //精确计算分数
                    int grade=GradeCompute.testTopicGrade(maskArr[1],paperTopic.getGrade());
                    paperTopic.setGrade(grade);
                }else if(paperTopic.getType()==TopicConstant.RADIOES){ //单选
                    GradeCompute.radioesGrade(paperTopic, (Radioes) topic);
                    //精确计算分数
                    int grade=GradeCompute.testTopicGrade(maskArr[0],paperTopic.getGrade());
                    paperTopic.setGrade(grade);
                }else { //判断
                    GradeCompute.judgeGrade(paperTopic, (Judgment) topic);
                    //精确计算分数
                    int grade=GradeCompute.testTopicGrade(maskArr[2],paperTopic.getGrade());
                    paperTopic.setGrade(grade);
                }
            }
        }
        //保存到paper_topic
        paperMapper.insertPaperTopic(paperTopics);
        //给test打分
        Integer totalGrade=testMapper.totalGrade(id);
        test.setGrade(totalGrade);
        //更新test
        testMapper.updateGrade(test);
    }

    //填充paperDetailVO中的list字段
    private void topicListFill(PaperDetail paperDetail,PaperDetailVO paperDetailVO,boolean eraseAnswer){
//        List radIds=null,mulIds=null,judgeIds=null,fitbIds=null,vocIds=null;
//        if(paperDetail.getRadioIds()!=null)
//            radIds= List.of(paperDetail.getRadioIds().split(PaperConstant.ID_APART));
//        if(paperDetail.getMulIds()!=null)
//            mulIds= List.of(paperDetail.getMulIds().split(PaperConstant.ID_APART));
//        if(paperDetail.getJudgIds()!=null)
//            judgeIds= List.of(paperDetail.getJudgIds().split(PaperConstant.ID_APART));
//        if(paperDetail.getFitbIds()!=null)
//            fitbIds= List.of(paperDetail.getFitbIds().split(PaperConstant.ID_APART));
//        if(paperDetail.getVocIds()!=null)
//            vocIds= List.of(paperDetail.getVocIds().split(PaperConstant.ID_APART));
//        TopicPublic topicPublic;
//        if(radIds!=null){
//            log.info("radIds：{}",radIds);
//            topicPublic=topicService.getTopicMapper(TopicConstant.RADIOES);
//            paperDetailVO.setRadioList(topicPublic.getByIds(radIds));
//            log.info("radioList：{}",paperDetailVO.getRadioList());
//        }
//        if(mulIds!=null){
//            log.info("mulIds：{}",mulIds);
//            topicPublic=topicService.getTopicMapper(TopicConstant.MULTIPLE_CHOICES);
//            paperDetailVO.setMulList(topicPublic.getByIds(mulIds));
//            log.info("mulList：{}",paperDetailVO.getMulList());
//        }
//        if(judgeIds!=null){
//            log.info("judgeIds：{}",judgeIds);
//            topicPublic=topicService.getTopicMapper(TopicConstant.JUDGMENT);
//            paperDetailVO.setJudgList(topicPublic.getByIds(judgeIds));
//            log.info("judgeList：{}",paperDetailVO.getJudgList());
//        }
//        if(fitbIds!=null){
//            log.info("fitbIds：{}",fitbIds);
//            topicPublic=topicService.getTopicMapper(TopicConstant.FILL_IN_THE_BLANK);
//            paperDetailVO.setFitbList(topicPublic.getByIds(fitbIds));
//            log.info("fitbList：{}",paperDetailVO.getFitbList());
//        }
//        if(vocIds!=null){
//            log.info("vocIds：{}",vocIds);
//            topicPublic=topicService.getTopicMapper(TopicConstant.VOCABULARY_QST);
//            paperDetailVO.setVocList(topicPublic.getByIds(vocIds));
//            log.info("vocList：{}",paperDetailVO.getVocList());
//        }
        //优雅的重构上述代码************** ^_^
        List<List> idList=new ArrayList<>();
        int[] types=TopicConstant.TOPIC_TYPE_ARRAY;
        //装填id_list
        for (int i = 0; i < 5; i++) {
            if(paperDetail.getIds(types[i])!=null){
                idList.add(List.of(paperDetail.getIds(i+1).split(PaperConstant.ID_APART)));
            }else {
                idList.add(null);
            }
        }
        //装填PaperDetailVO的topic_list们
        TopicPublic topicPublic;
        List topicList;
        for (int i = 0; i <5 ; i++) {
            if(idList.get(i)!=null){
                topicPublic=topicService.getTopicMapper(types[i]);
                topicList=topicPublic.getByIds(idList.get(i));
                paperDetailVO.setTopicList(types[i], topicList);
                System.out.print("["+topicList.size()+"]，");
            }
        }
        if(eraseAnswer)
            paperDetailVO.setNullAnswer();
    }

    //根据paper_id获取paper每种题型每个多少分
    public int[]  getMask(Long paperId){
        int[] maskArr=new int[5];
        PaperDetail paperDetail=paperMapper.getPaperDetail(paperId);
        List idList;
        for (int i = 0; i < 5; i++) {
            if(paperDetail.getMask(TOPIC_TYPE_ARRAY[i])==0)
                maskArr[i]=0;
            else {
                idList= List.of((paperDetail.getIds(TOPIC_TYPE_ARRAY[i]).split(PaperConstant.ID_APART)));
                log.info("idList:{}",idList);
                maskArr[i]=paperDetail.getMask(TOPIC_TYPE_ARRAY[i])/idList.size();
            }
        }
        return maskArr;
    }

    //随机组卷
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result randomC(TestRdmDTO testRdmDTO) throws Exception {
        Paper paper=new Paper(testRdmDTO);
        //写入paper表，获取写入id
        paperMapper.insert(paper);
        PaperDetail paperDetail=new PaperDetail(testRdmDTO, paper.getId());
        //随机组卷
        List topicList=new ArrayList<>();
        for(int i=0; i<5; ++i){
            int[] topicDistr;
            if(paperDetail.getMask(TOPIC_TYPE_ARRAY[i])>0){
                //获取难度分布数据
                topicDistr=topicCountCompute(Character.getNumericValue(paper.getDifficulty()),testRdmDTO.getTopicNumbers().get(i));
                for (int j = 0; j < 5; j++) {   //检索不同难度数据库，随机获取题目id
                    if(topicDistr[j]>0){
                        System.out.println(TOPIC_TYPE_ARRAY[j]+"——difficulty："+(j+1)+"——count："+topicDistr[j]);
                        List<Integer> list=topicService.getTopicMapper(TOPIC_TYPE_ARRAY[i]).randomC(j+1,topicDistr[j]);
                        if(list.size()<topicDistr[j])
                            throw new Exception(MsgConstant.Lack_TOPIC);
                        topicList.addAll(list);
                    }
                }
                //题目检索完毕，转箱成String保存
                paperDetail.setIds(TOPIC_TYPE_ARRAY[i],topicList);
                topicList.clear();
            }
        }
        paperMapper.insertDetail(paperDetail);
        return Result.success(paper);
    }

    @Override
    public Result update(Paper paper) {
        paperMapper.update(paper);
        return Result.success();
    }

    //传入难度，总题目数，输出各个难度题目数
    private int[] topicCountCompute(int difficulty,int topicTotal){
        int[] topicDistr=new int[5];
        double[] difficultyDistr ;
        switch (difficulty) {
            case 1:
                difficultyDistr = new double[]{0.4, 0.3, 0.1, 0.1, 0.1};break;
            case 2:
                difficultyDistr = new double[]{0.2, 0.4, 0.2, 0.1, 0.1};break;
            case 3:
                difficultyDistr = new double[]{0.1, 0.2, 0.4, 0.2, 0.1};break;
            case 4:
                difficultyDistr = new double[]{0.1, 0.1, 0.2, 0.4, 0.2};break;
            case 5:
                difficultyDistr = new double[]{0.1, 0.1, 0.1, 0.3, 0.4};break;
            default:
                difficultyDistr = new double[]{0.2, 0.2, 0.2, 0.2, 0.2};
        }
        int sum=0;
        for (int i = 0; i < 5; i++) {
            topicDistr[i]= (int) (topicTotal*difficultyDistr[i]);
            sum+=topicDistr[i];
        }
        topicDistr[difficulty-1]+=(topicTotal-sum);
        return topicDistr;
    }
}
