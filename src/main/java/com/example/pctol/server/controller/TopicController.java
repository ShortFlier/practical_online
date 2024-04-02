package com.example.pctol.server.controller;

import com.example.pctol.pojo.VO.Result;
import com.example.pctol.server.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 题目
 *
 * @author hp
 * @date 2024/3/24
 */
@RestController
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    private TopicService topicService;

    //统计题数
    @GetMapping("/statistic/{type}")
    public Result statistic(@PathVariable int type) throws Exception {
        Result result=topicService.statistic(type);
        return result;
    }
}
