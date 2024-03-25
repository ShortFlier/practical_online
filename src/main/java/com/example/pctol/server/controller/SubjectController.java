package com.example.pctol.server.controller;

import com.example.pctol.common.constant.StateCode;
import com.example.pctol.pojo.VO.Result;
import com.example.pctol.pojo.entity.Subject;
import com.example.pctol.server.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
