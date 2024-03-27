package com.example.pctol.server.controller;

import com.example.pctol.common.constant.StateCode;
import com.example.pctol.pojo.DTO.SubSearchDTO;
import com.example.pctol.pojo.VO.PageResult;
import com.example.pctol.pojo.VO.Result;
import com.example.pctol.pojo.entity.Subject;
import com.example.pctol.server.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hp
 * @date 2024/3/25
 */
@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @PutMapping("/add")
    public Result add(@RequestBody Subject subject){
        subjectService.add(subject);
        return new Result(StateCode.SUCCESS);
    }

    //分页查询获取匹配的学科（审核通过)
    @PostMapping("/gets")
    public Result getData(@RequestBody SubSearchDTO subSearchDTO){
        PageResult pageResult=subjectService.getData(subSearchDTO);
        return new Result(StateCode.SUCCESS, pageResult);
    }
}
