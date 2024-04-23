package com.example.pctol.server.controller;

import com.example.pctol.pojo.DTO.StuTestHisDTO;
import com.example.pctol.pojo.DTO.TestRdmDTO;
import com.example.pctol.pojo.VO.Result;
import com.example.pctol.server.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hp
 * @date 2024/4/22
 */
@RequestMapping("/test")
@RestController
@Slf4j
public class TestController {
    @Autowired
    private TestService testService;

    //获取考试历史
    @GetMapping("/history")
    public Result getHis(StuTestHisDTO stuTestHisDTO){
        log.info("**********************************[/test/history]************************************");
        log.info("搜索信息：{}",stuTestHisDTO);
        stuTestHisDTO.setStart();
        Result result=testService.getHis(stuTestHisDTO);
        return result;
    }

    @GetMapping("/look/{testId}")
    public Result testLook(@PathVariable Long testId){
        log.info("**********************************[/test/look]************************************");
        log.info("test_id：{}",testId);
        Result result=testService.testLook(testId,false);
        return result;
    }


    @PutMapping("/random")
    public Result randomCrt(@RequestBody TestRdmDTO testRdmDTO){
        log.info("**********************************[/test/random]************************************");
        log.info("组卷信息：{}",testRdmDTO);
        Result result;
        return null;
    }
}
