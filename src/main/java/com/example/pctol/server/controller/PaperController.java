package com.example.pctol.server.controller;

import com.example.pctol.pojo.DTO.PaperSearchDTO;
import com.example.pctol.pojo.VO.Result;
import com.example.pctol.server.service.PaperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hp
 * @date 2024/4/15
 */
@RestController
@RequestMapping("/paper")
@Slf4j
public class PaperController {
    @Autowired
    private PaperService paperService;

    //获取试卷信息，根据科目和难度,或者标题、上传者
    @PostMapping("/gets")
    public Result gets(@RequestBody PaperSearchDTO paperSearchDTO){
        log.info("**********************************[/practice/add]************************************");
        log.info("查询信息：{}",paperSearchDTO);
        Result result=paperService.gets(paperSearchDTO);
        return result;
    }
}
