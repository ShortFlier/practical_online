package com.example.pctol.server.controller;

import com.example.pctol.common.constant.StateCode;
import com.example.pctol.pojo.DTO.SubSearchDTO;
import com.example.pctol.pojo.VO.PageResult;
import com.example.pctol.pojo.VO.Result;
import com.example.pctol.pojo.entity.Subject;
import com.example.pctol.server.service.SubjectService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hp
 * @date 2024/3/25
 */
@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    SubjectService subjectService;

//    管理员添加学科，不需要审核直接通过
    @PutMapping("/admin/add")
    public Result adminAdd(@RequestBody Subject subject){
        subjectService.adminAdd(subject);
        return new Result(StateCode.SUCCESS,"操作成功");
    }

    @PutMapping("/add")
    public Result add(@RequestBody Subject subject){
        subjectService.add(subject);
        return new Result(StateCode.SUCCESS);
    }

    //分页查询获取匹配的学科（审核通过)
    @PostMapping("/gets")
    public Result getData(@RequestBody SubSearchDTO subSearchDTO) throws Exception {
        PageResult pageResult=subjectService.getData(subSearchDTO);
        return new Result(StateCode.SUCCESS, pageResult);
    }

    @GetMapping("/like/{name}")
    public Result getLikeName(@PathVariable String name){
        Result result=subjectService.getLikeName(name);
        return result;
    }

    @DeleteMapping("/dle/{name}")
    public Result subDle(@PathVariable String name){
        subjectService.dle(name);
        return Result.success();
    }
}
