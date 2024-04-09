package com.example.pctol.server.controller;

import com.example.pctol.pojo.DTO.LoginDTO;
import com.example.pctol.pojo.DTO.StuSearchDTO;
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
        log.info("**********************************[/student/login]************************************");
        log.info("student login--account: {}, password: {}",loginInfo.getAccount(),loginInfo.getPassword());
        Result result=studentService.login(loginInfo);
        return result;
    }

    @PostMapping("/gets")
    public Result pageGet(@RequestBody StuSearchDTO stuSearchDTO) throws Exception {
        log.info("**********************************[/student/gets]************************************");
        Result result=studentService.pageGet(stuSearchDTO);
        return result;
    }

    @GetMapping("/getActs")
    public Result getAct() throws Exception {
        log.info("**********************************[/student/getActs]************************************");
        Result result=studentService.getAct();
        return result;
    }

    @GetMapping("/{account}")
    public Result getInfo(@PathVariable String account){
        log.info("**********************************[/student/account]************************************");
        Result result=studentService.getInfoByAct(account);
        return result;
    }
}
