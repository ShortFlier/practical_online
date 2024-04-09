package com.example.pctol.server.controller;

import com.example.pctol.common.constant.MsgConstant;
import com.example.pctol.pojo.DTO.TopicSearchInfoDTO;
import com.example.pctol.pojo.DTO.TopicUpdateDTO;
import com.example.pctol.pojo.VO.Result;
import com.example.pctol.server.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 题目
 *
 * @author hp
 * @date 2024/3/24
 */
@RestController
@RequestMapping("/topic")
@Slf4j
public class TopicController {
    @Autowired
    private TopicService topicService;

    //统计题数
    @GetMapping("/statistic/{type}")
    public Result statistic(@PathVariable int type) throws Exception {
        Result result=topicService.statistic(type);
        return result;
    }

    //获取未审核的题目
    @GetMapping("/audit")
    public Result gets(TopicSearchInfoDTO topicSearchInfoDTO){
        log.info("查询信息：{}",topicSearchInfoDTO);
        Result result=topicService.gets(topicSearchInfoDTO);
        return result;
    }

    //更新状态
    @PutMapping("/update")
    public Result update(@RequestBody TopicUpdateDTO topicUpdateDTO){
        log.info("更新信息：{}",topicUpdateDTO);
        topicService.update(topicUpdateDTO);
        return Result.success(MsgConstant.SUCCESS);
    }
}
