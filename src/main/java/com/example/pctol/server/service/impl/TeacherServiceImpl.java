package com.example.pctol.server.service.impl;

import com.example.pctol.common.constant.JwtClaimsConstant;
import com.example.pctol.common.constant.StateCode;
import com.example.pctol.common.properties.JWTproperties;
import com.example.pctol.pojo.DTO.LoginDTO;
import com.example.pctol.pojo.VO.Result;
import com.example.pctol.server.mapper.TeacherMapper;
import com.example.pctol.server.service.TeacherService;
import com.example.pctol.common.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.pctol.pojo.entity.Teacher;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hp
 * @date 2024/3/24
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private JWTproperties jwtProperties;
    @Autowired
    private TeacherMapper teacherMapper;
    @Override
    public Result login(LoginDTO loginInfo) {
        Teacher teacher=teacherMapper.login(loginInfo.getAccount());
        //账号不存在
        if (teacher==null)
            return new Result(StateCode.ACCOUNT_NOT_EXIST,"账号不存在");

        if(loginInfo.getPassword().equals(teacher.getPassword())){
            //登录成功后，生成jwt令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put(JwtClaimsConstant.TEACHER_ID, teacher.getThId());
            String token = JwtUtil.createJWT(
                    jwtProperties.getTeacherSecretKey(),
                    jwtProperties.getTeacherTtl(),
                    claims);
            return new Result(StateCode.SUCCESS,token,teacher);
        }else //密码错误
            return new Result(StateCode.PASSWORD_ERROR,"密码错误");
    }
}
