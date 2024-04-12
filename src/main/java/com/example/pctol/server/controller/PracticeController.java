package com.example.pctol.server.controller;

import com.example.pctol.common.constant.MsgConstant;
import com.example.pctol.pojo.VO.Result;
import com.example.pctol.pojo.entity.Practice;
import com.example.pctol.server.service.PracticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hp
 * @date 2024/4/12
 */
@RestController
@RequestMapping("/practice")
@Slf4j
public class PracticeController {
    @Autowired
    private PracticeService practiceService;

    @PostMapping("/add")
    public Result add(@RequestBody Practice practice){
        log.info("**********************************[/practice/add]************************************");
        log.info("练习提交：{}",practice);
        practiceService.add(practice);
        return Result.success(MsgConstant.SUCCESS);
    }
}
