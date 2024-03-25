package com.example.pctol.server.service.impl;

import com.example.pctol.common.constant.StateCode;
import com.example.pctol.common.properties.JWTproperties;
import com.example.pctol.pojo.DTO.LoginDTO;
import com.example.pctol.pojo.VO.Result;
import com.example.pctol.pojo.entity.Admin;
import com.example.pctol.server.mapper.AdminMapper;
import com.example.pctol.server.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.pctol.common.constant.JwtClaimsConstant;
import com.example.pctol.common.utils.JwtUtil;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hp
 * @date 2024/3/24
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private JWTproperties jwtProperties;
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public Result login(LoginDTO admin) {
        Admin adminInfo=adminMapper.login(admin.getAccount());
        //账号不存在
        if (adminInfo==null)
            return new Result(StateCode.ACCOUNT_NOT_EXIST,"账号不存在");

        if(admin.getPassword().equals(adminInfo.getPassword())){
            //jwt令牌生成
            Map<String,Object> claims=new HashMap<>();
            claims.put(JwtClaimsConstant.ADMIN_INFO,"[admin]:"+admin.getAccount());
            String token= JwtUtil.createJWT(
                    jwtProperties.getAdminSecretKey(),
                    jwtProperties.getAdminTtl(),
                    claims);
            return new Result(StateCode.SUCCESS,token, adminInfo);
        }else //密码错误
            return new Result(StateCode.PASSWORD_ERROR,"密码错误");

    }
}
