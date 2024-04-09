package com.example.pctol.server.controller;

import com.example.pctol.pojo.DTO.LoginDTO;
import com.example.pctol.pojo.VO.Result;
import com.example.pctol.server.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hp
 * @date 2024/3/24
 */
@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    @Autowired
    private AdminService adminService;
    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO admin){
        log.info("**********************************[/admin/login]************************************");
        log.info("admin login--account: {}, password: {}",admin.getAccount(),admin.getPassword());
        Result result=adminService.login(admin);
        return result;
    }
}
