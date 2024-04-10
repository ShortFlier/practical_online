package com.example.pctol.server.controller;

import com.example.pctol.pojo.DTO.LoginDTO;
import com.example.pctol.pojo.DTO.ThSearchDTO;
import com.example.pctol.pojo.VO.Result;
import com.example.pctol.pojo.entity.Teacher;
import com.example.pctol.server.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hp
 * @date 2024/3/24
 */
@RestController
@RequestMapping("/teacher")
@Slf4j
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO loginInfo) {
        log.info("**********************************[/teacher/login]************************************");
        log.info("teacher login--account: {}, password: {}",loginInfo.getAccount(),loginInfo.getPassword());
        Result result=teacherService.login(loginInfo);
        return result;
    }

    @PostMapping("/gets")
    public Result pageGet(@RequestBody ThSearchDTO thSearchDTO) throws Exception {
        log.info("**********************************[/teacher/gets]************************************");
        System.out.println(thSearchDTO);
        Result result=teacherService.pageGet(thSearchDTO);
        return result;
    }

    @GetMapping("/getActs")
    public Result getAct(){
        log.info("**********************************[/teacher/getActs]************************************");
        Integer total=teacherService.getAct();
        return Result.success(total);
    }

    @GetMapping("/{account}")
    public Result getInfo(@PathVariable String account){
        log.info("**********************************[/teacher/account]************************************");
        Result result=teacherService.getInfoByAct(account);
        return result;
    }

    @PostMapping("/register")
    public Result regist(@RequestBody Teacher teacher) throws Exception {
        log.info("**********************************[/teacher/register]************************************");
        log.info("教师注册信息：{}",teacher);
        Result result=teacherService.regit(teacher);
        return result;
    }
}
