package com.example.pctol.server.service.impl;

import com.example.pctol.common.constant.MsgConstant;
import com.example.pctol.common.constant.PaperConstant;
import com.example.pctol.common.constant.StateCode;
import com.example.pctol.common.constant.TopicConstant;
import com.example.pctol.common.utils.Util;
import com.example.pctol.pojo.DTO.PaperSearchDTO;
import com.example.pctol.pojo.VO.*;
import com.example.pctol.pojo.entity.Paper;
import com.example.pctol.pojo.entity.Topic;
import java.util.ArrayList;
import java.util.List;

import com.example.pctol.pojo.entity.PaperDetail;
import com.example.pctol.server.mapper.PaperMapper;
import com.example.pctol.server.mapper.SubjectMapper;
import com.example.pctol.server.mapper.TopicPublic;
import com.example.pctol.server.service.PaperService;
import com.example.pctol.server.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Result getByIdNt(long id) {
//        获取Paper
        Paper paper=paperMapper.getPaper(id);
//        获取PaperDetail
        PaperDetail paperDetail=paperMapper.getPaperDetail(id);
        PaperDetailVO paperDetailVO=new PaperDetailVO(paper,paperDetail);
        //填充paperDetailVO中的list字段
        topicListFill(paperDetail,paperDetailVO,true);
        return Result.success(paperDetailVO);
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
        int[] types={TopicConstant.RADIOES,TopicConstant.MULTIPLE_CHOICES,TopicConstant.JUDGMENT,
                TopicConstant.FILL_IN_THE_BLANK,TopicConstant.VOCABULARY_QST};
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
}
