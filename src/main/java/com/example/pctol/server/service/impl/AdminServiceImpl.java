package com.example.pctol.server.service.impl;

import com.example.pctol.common.constant.StateCode;
import com.example.pctol.pojo.DTO.LoginVO;
import com.example.pctol.pojo.VO.Result;
import com.example.pctol.pojo.entity.Admin;
import com.example.pctol.server.mapper.AdminMapper;
import com.example.pctol.server.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hp
 * @date 2024/3/24
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public Result login(LoginVO admin) {
        Admin adminInfo=adminMapper.login(admin.getAccount());
        //账号不存在
        if (adminInfo==null)
            return new Result(StateCode.ACCOUNT_NOT_EXIST,"账号不存在");

        if(admin.getPassword().equals(adminInfo.getPassword())){
            return new Result(StateCode.SUCCESS,"登入成功", adminInfo);
        }else //密码错误
            return new Result(StateCode.PASSWORD_ERROR,"密码错误");

    }
}
