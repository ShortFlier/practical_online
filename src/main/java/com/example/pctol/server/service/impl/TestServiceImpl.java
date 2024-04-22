package com.example.pctol.server.service.impl;

import com.example.pctol.common.constant.TopicConstant;
import com.example.pctol.common.utils.Util;
import com.example.pctol.pojo.DTO.StuTestHisDTO;
import com.example.pctol.pojo.VO.*;
import com.example.pctol.pojo.entity.Test;
import com.example.pctol.pojo.entity.Topic;
import com.example.pctol.server.mapper.SubjectMapper;
import com.example.pctol.server.mapper.TestMapper;
import com.example.pctol.server.service.PaperService;
import com.example.pctol.server.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hp
 * @date 2024/4/22
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestMapper testMapper;
    @Autowired
    private SubjectMapper subjectMapper;
    @Autowired
    private PaperService paperService;

    @Override
    public Result getHis(StuTestHisDTO stuTestHisDTO) {
        List<TestVO> testVOs=testMapper.getHis(stuTestHisDTO);
        //获取SubVO数组
        List<SubVO> subVOList=subjectMapper.getList();
        for (TestVO testVO: testVOs) {
            String name= Util.getSubjectName(testVO.getSubjectId(), subVOList);
            testVO.setSubjectName(name);
        }
        return Result.success(testVOs);
    }

    @Override
    public Result testLook(Long testId,boolean eraseAnswer) {
        //获取试卷id
        Test test=testMapper.getById(testId);
        Result result=paperService.getByIdNt(test.getPaperId(),eraseAnswer);
        PaperDetailVO paperDetailVO = (PaperDetailVO) result.getData();
        //改造paperDetailVO，替换List内容，为其增加用户回答
        TopicTestVO topicTestVO;
        for (int i = 0; i < 5; i++) {
            if(paperDetailVO.getMask(TopicConstant.TOPIC_TYPE_ARRAY[i])!=0){
                List<TopicTestVO> topicTestVOList=new ArrayList<>();
                for (Topic topic: paperDetailVO.getTopicList(TopicConstant.TOPIC_TYPE_ARRAY[i])) {
                    topicTestVO=testMapper.getSmtAsw(testId,topic.getId(),TopicConstant.TOPIC_TYPE_ARRAY[i]);
                    topicTestVO.setTopic(topic);
                    topicTestVOList.add(topicTestVO);
                }
                paperDetailVO.setTopicList(TopicConstant.TOPIC_TYPE_ARRAY[i],topicTestVOList);
            }
        }

        return Result.success(paperDetailVO);
    }
}
