package com.example.pctol.server.controller;

import com.example.pctol.common.constant.MsgConstant;
import com.example.pctol.pojo.DTO.PaperSearchDTO;
import com.example.pctol.pojo.DTO.SmtPaperDTO;
import com.example.pctol.pojo.DTO.TestRdmDTO;
import com.example.pctol.pojo.VO.Result;
import com.example.pctol.server.service.PaperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        log.info("**********************************[/paper/gets]************************************");
        log.info("查询信息：{}",paperSearchDTO);
        paperSearchDTO.difCheck();
        Result result=paperService.gets(paperSearchDTO);
        return result;
    }

    //获取试卷，不带答案
    @GetMapping("/look/{id}")
    public Result getByIdNotAnswer(@PathVariable long id){
        log.info("**********************************[/paper/look/{id}]************************************");
        log.info("试卷id：{}",id);
        Result result=paperService.getByIdNt(id,true);
        return result;
    }

    //获取试卷，带答案
    @GetMapping("/looks/{id}")
    public Result getByIdAnswer(@PathVariable long id){
        log.info("**********************************[/paper/looks/{id}]************************************");
        log.info("试卷id：{}",id);
        Result result=paperService.getByIdNt(id,false);
        return result;
    }

    //考试
    @GetMapping("/test/{id}")
    public Result test(@PathVariable long id){
        log.info("**********************************[/paper/test/{id}]************************************");
        log.info("试卷id：{}",id);
        Result result=paperService.test(id);
        return result;
    }

    //提交试卷
    @PutMapping("/test/submit")
    public Result testSubmit(@RequestBody SmtPaperDTO smtPaperDTO){
        log.info("**********************************[/paper/test/submit]************************************");
        log.info("交卷信息：{}",smtPaperDTO);
        paperService.testSubmit(smtPaperDTO);
        return Result.success(MsgConstant.SUCCESS);
    }

    @PutMapping("/random")
    public Result randomCrt(@RequestBody TestRdmDTO testRdmDTO) throws Exception {
        log.info("**********************************[/paper/random]************************************");
        log.info("组卷信息：{}",testRdmDTO);
        Result result=paperService.randomC(testRdmDTO);
        return result;
    }
}
