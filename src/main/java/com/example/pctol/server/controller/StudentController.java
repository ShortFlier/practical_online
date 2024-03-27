package com.example.pctol.server.controller;

import com.example.pctol.pojo.DTO.LoginDTO;
import com.example.pctol.pojo.VO.Result;
import com.example.pctol.server.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hp
 * @date 2024/3/24
 */
@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO loginInfo){
        log.info("student login--account: {}, password: {}",loginInfo.getAccount(),loginInfo.getPassword());
        Result result=studentService.login(loginInfo);
        return result;
    }
}
