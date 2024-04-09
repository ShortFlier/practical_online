package com.example.pctol.server.controller;

import com.example.pctol.common.constant.MsgConstant;
import com.example.pctol.common.constant.StateCode;
import com.example.pctol.pojo.DTO.SubSearchDTO;
import com.example.pctol.pojo.DTO.SubUpdateInfoDTO;
import com.example.pctol.pojo.VO.PageResult;
import com.example.pctol.pojo.VO.Result;
import com.example.pctol.pojo.entity.Subject;
import com.example.pctol.server.service.SubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author hp
 * @date 2024/3/25
 */
@RestController
@RequestMapping("/subject")
@Slf4j
public class SubjectController {
    @Autowired
    SubjectService subjectService;

//    管理员添加学科，不需要审核直接通过
    @PutMapping("/admin/add")
    public Result adminAdd(@RequestBody Subject subject){
        log.info("**********************************[/subject/admin/add]************************************");
        subjectService.adminAdd(subject);
        return new Result(StateCode.SUCCESS,"操作成功");
    }

    @PutMapping("/add")
    public Result add(@RequestBody Subject subject){
        log.info("**********************************[/subject/add]************************************");
        subjectService.add(subject);
        return new Result(StateCode.SUCCESS);
    }

    //分页查询获取匹配的学科（审核通过)
    @PostMapping("/gets")
    public Result getData(@RequestBody SubSearchDTO subSearchDTO) throws Exception {
        log.info("**********************************[/subject/gets]************************************");
        System.out.println(subSearchDTO);
        PageResult pageResult=subjectService.getData(subSearchDTO);
        return new Result(StateCode.SUCCESS, pageResult);
    }

    @GetMapping("/like/{name}")
    public Result getLikeName(@PathVariable String name){
        log.info("**********************************[/subject/like/]************************************");
        Result result=subjectService.getLikeName(name);
        return result;
    }

    @DeleteMapping("/dle/{name}")
    public Result subDle(@PathVariable String name) throws Exception {
        log.info("**********************************[/subject/dle/]************************************");
        subjectService.dle(name);
        return Result.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody SubUpdateInfoDTO subUpdateInfoDTO) throws Exception {
        log.info("**********************************[/subject/update]************************************");
        System.out.println("subUpdateInfo:"+ subUpdateInfoDTO);
        subUpdateInfoDTO.setAuditTime(LocalDateTime.now());
        subjectService.update(subUpdateInfoDTO);
        return Result.success(MsgConstant.SUCCESS);
    }

    @GetMapping("/get")
    public Result getByAudit(Integer auditState,Integer page, Integer pageSize){
        log.info("**********************************[/subject/get]************************************");
        log.info("state:{},page：{},pageSize:{}",auditState,page,pageSize);
        Result result=subjectService.getByAudit(auditState, page, pageSize);
        return result;
    }

    @GetMapping("/list")
    public Result getSubList(){
        log.info("**********************************[/subject/list]************************************");
        Result result=subjectService.getList();
        return result;
    }
}
