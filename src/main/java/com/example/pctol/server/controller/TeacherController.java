package com.example.pctol.server.controller;

import com.example.pctol.pojo.DTO.LoginDTO;
import com.example.pctol.pojo.DTO.ThSearchDTO;
import com.example.pctol.pojo.VO.Result;
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
        log.info("teacher login--account: {}, password: {}",loginInfo.getAccount(),loginInfo.getPassword());
        Result result=teacherService.login(loginInfo);
        return result;
    }

    @PostMapping("/gets")
    public Result pageGet(@RequestBody ThSearchDTO thSearchDTO) throws Exception {
        Result result=teacherService.pageGet(thSearchDTO);
        return result;
    }

    @GetMapping("/getActs")
    public Result getAct(){
        Integer total=teacherService.getAct();
        return Result.success(total);
    }

    @GetMapping("/{account}")
    public Result getInfo(@PathVariable String account){
        Result result=teacherService.getInfoByAct(account);
        return result;
    }
}
