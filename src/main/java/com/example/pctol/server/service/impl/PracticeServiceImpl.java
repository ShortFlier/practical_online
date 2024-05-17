package com.example.pctol.server.service.impl;

import com.example.pctol.common.constant.TopicConstant;
import com.example.pctol.common.utils.GradeCompute;
import com.example.pctol.common.utils.Util;
import com.example.pctol.pojo.VO.GradeSechVO;
import com.example.pctol.pojo.VO.PracticeDateVO;
import com.example.pctol.pojo.VO.TopicVO;
import com.example.pctol.pojo.entity.*;
import com.example.pctol.server.mapper.PracticeMapper;
import com.example.pctol.server.mapper.TopicPublic;
import com.example.pctol.server.service.PracticeService;
import com.example.pctol.server.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author hp
 * @date 2024/4/12
 */
@Service
@Slf4j
public class PracticeServiceImpl implements PracticeService {
    @Autowired
    private PracticeMapper practiceMapper;
    @Autowired
    private TopicService topicService;

    public void gradeCpt(Practice practice){
        if(practice.getType()<TopicConstant.RADIOES||practice.getType()>TopicConstant.JUDGMENT){
            log.info("原始答案：{}",practice.getSubmitAnswer());
            String[] arr=practice.getSubmitAnswer().split(TopicConstant.ANSWER_APART);
            log.info("分割答案--空数：{}\n答案：{}",arr.length,arr);
            return;
        }
        List<Integer> ids=new ArrayList<>();
        ids.add(Math.toIntExact(practice.getTopicId()));
        TopicPublic topicMapper=topicService.getTopicMapper(practice.getType());
        Topic topic =topicMapper.getByIds(ids).get(0);
        if(practice.getType()==TopicConstant.MULTIPLE_CHOICES){ //多选
            GradeCompute.mulChoGrade(practice, (MultipleChoices) topic);
        }else if(practice.getType()==TopicConstant.RADIOES){ //单选
            GradeCompute.radioesGrade(practice, (Radioes) topic);
        }else { //判断
            GradeCompute.judgeGrade(practice, (Judgment) topic);
        }
    }

    @Override
    public void add(Practice practice) {
        //多选题答案格式规划
        if(practice.getType()==TopicConstant.MULTIPLE_CHOICES){
            practice.setSubmitAnswer(Util.formatMluChoices(practice.getSubmitAnswer()));
        }
        gradeCpt(practice);
        practiceMapper.add(practice);
    }

    @Override
    public List all(GradeSechVO gradeSechVO) {
        List<Integer> list=new ArrayList<>();
        for (int i=0;i<5;++i){
            List<TopicVO> topicVOList=practiceMapper.all(gradeSechVO,TopicConstant.TOPIC_TYPE_ARRAY[i]);
            if(topicVOList==null){
                list.add(0);
            }else {
                list.add(topicVOList.size());
            }
        }
        return list;
    }

    @Override
    public int[] rate(GradeSechVO gradeSechVO) {
        int[] list=new int[7];
        //获取单选
        List<Practice> practices=practiceMapper.rate(gradeSechVO,TopicConstant.RADIOES);
        for (int i = 0; i < practices.size(); i++) {
            if(practices.get(i).getGrade()==100)
                ++list[0];
            else
                ++list[1];
        }
        //获取多选
        practices=practiceMapper.rate(gradeSechVO,TopicConstant.MULTIPLE_CHOICES);
        for (int i = 0; i < practices.size(); i++) {
            if(practices.get(i).getGrade()==100)
                ++list[2];
            else if(practices.get(i).getGrade()==50)
                ++list[3];
            else
                ++list[4];
        }
        //获取判断
        practices=practiceMapper.rate(gradeSechVO,TopicConstant.JUDGMENT);
        for (int i = 0; i < practices.size(); i++) {
            if(practices.get(i).getGrade()==100)
                ++list[5];
            else
                ++list[6];
        }
        return list;
    }

    @Override
    public PracticeDateVO date(GradeSechVO gradeSechVO) {
        List<LocalDate> dateList=practiceMapper.dateGet(gradeSechVO);
        Set<LocalDate> dateTime=new HashSet<>();
        List<String> date=new ArrayList<>();
        List<Integer> numbers=new ArrayList<>();
        for (int i = 0; i < dateList.size(); i++) {
            if(!dateTime.add(dateList.get(i))){
                //最后一个元素加1
                int number=numbers.get(numbers.size()-1)+1;
                numbers.set(numbers.size()-1,number);
            }else {
                numbers.add(1);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d");
                String dateString = dateList.get(i).format(formatter);
                date.add(dateString);
            }
        }
        PracticeDateVO practiceDateVO=new PracticeDateVO(dateTime,date,numbers);
        System.out.println(practiceDateVO);
        return practiceDateVO;
    }
}
