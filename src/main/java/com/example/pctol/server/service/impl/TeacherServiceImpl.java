package com.example.pctol.server.service.impl;

import com.example.pctol.common.constant.JwtClaimsConstant;
import com.example.pctol.common.constant.MsgConstant;
import com.example.pctol.common.constant.StateCode;
import com.example.pctol.common.properties.JWTproperties;
import com.example.pctol.common.utils.FormatCheck;
import com.example.pctol.common.utils.Util;
import com.example.pctol.pojo.DTO.LoginDTO;
import com.example.pctol.pojo.DTO.ThSearchDTO;
import com.example.pctol.pojo.VO.PageResult;
import com.example.pctol.pojo.VO.Result;
import com.example.pctol.server.mapper.TeacherMapper;
import com.example.pctol.server.service.TeacherService;
import com.example.pctol.common.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.pctol.pojo.entity.Teacher;

import java.util.HashMap;
import java.util.List;
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
        loginInfo.md5Pwd();
        Teacher teacher=teacherMapper.login(loginInfo.getAccount());
        //账号不存在
        if (teacher==null)
            return new Result(StateCode.ACCOUNT_NOT_EXIST,"账号不存在");

        if(loginInfo.getPassword().equals(teacher.getPassword())){
            //登录成功后，生成jwt令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put(JwtClaimsConstant.TEACHER_ID,"[teacher]:"+teacher.getThId());
            String token = JwtUtil.createJWT(
                    jwtProperties.getTeacherSecretKey(),
                    jwtProperties.getTeacherTtl(),
                    claims);
            teacher.setPassword(null);
            return new Result(StateCode.SUCCESS,token,teacher);
        }else //密码错误
            return new Result(StateCode.PASSWORD_ERROR,"密码错误");
    }

    @Override
    public Result pageGet(ThSearchDTO thSearchDTO) throws Exception {
        int total=teacherMapper.getAct(thSearchDTO);
        if(total==0)
            throw new Exception(MsgConstant.NO_DATA);
        List<Teacher> list=teacherMapper.pageGet(thSearchDTO,thSearchDTO.getStart(),thSearchDTO.getPageSize());
        PageResult pageResult = new PageResult(total,list);
        return Result.success(pageResult);
    }

    @Override
    public Integer getAct() {
        int total=teacherMapper.getAct(new ThSearchDTO());
        return total;
    }

    @Override
    public Result getInfoByAct(String account) {
        Teacher teacher=teacherMapper.login(account);
        teacher.setPassword(null);
        return Result.success(teacher);
    }

    @Override
    public Result regit(Teacher teacher) throws Exception {
        //校验账号、密码、邮箱是否合法
        FormatCheck.checkAccount(teacher.getAccount());
        FormatCheck.checkPsd(teacher.getPassword());
        teacher.setPassword(Util.encrypt(teacher.getPassword()));
        FormatCheck.checkEmail(teacher.getEmail());
        teacherMapper.insert(teacher);
        //获取id
        long id=teacherMapper.login(teacher.getAccount()).getThId();
        return Result.success(MsgConstant.SUCCESS,id);
    }
}
