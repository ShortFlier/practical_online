package com.example.pctol.server.controller;

import com.example.pctol.common.constant.MsgConstant;
import com.example.pctol.pojo.VO.GradeSechVO;
import com.example.pctol.pojo.VO.PracticeDateVO;
import com.example.pctol.pojo.VO.Result;
import com.example.pctol.pojo.entity.Practice;
import com.example.pctol.server.service.PracticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/all")
    public Result all(@RequestBody GradeSechVO gradeSechVO){
        log.info("**********************************[/practice/all]************************************");
        log.info("搜索信息：{}",gradeSechVO);
        List arr=practiceService.all(gradeSechVO);
        return Result.success(arr);
    }

    @PostMapping("/rate")
    public Result rate(@RequestBody GradeSechVO gradeSechVO){
        log.info("**********************************[/practice/rate]************************************");
        log.info("搜索信息：{}",gradeSechVO);
        int[] arr=practiceService.rate(gradeSechVO);
        return Result.success(arr);
    }

    @PostMapping("/date")
    public Result dateCpt(@RequestBody GradeSechVO gradeSechVO){
        log.info("**********************************[/practice/date]************************************");
        log.info("搜索信息：{}",gradeSechVO);
        PracticeDateVO practiceDateVO=practiceService.date(gradeSechVO);
        return Result.success(practiceDateVO);
    }
}
