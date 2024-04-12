package com.example.pctol.server.service.impl;

import com.example.pctol.common.constant.TopicConstant;
import com.example.pctol.common.utils.GradeCompute;
import com.example.pctol.common.utils.Util;
import com.example.pctol.pojo.entity.*;
import com.example.pctol.server.mapper.PracticeMapper;
import com.example.pctol.server.mapper.TopicPublic;
import com.example.pctol.server.service.PracticeService;
import com.example.pctol.server.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
