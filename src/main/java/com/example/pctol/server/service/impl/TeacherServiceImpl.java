package com.example.pctol.server.service.impl;

import com.example.pctol.common.constant.StateCode;
import com.example.pctol.pojo.DTO.LoginVO;
import com.example.pctol.pojo.VO.Result;
import com.example.pctol.server.mapper.TeacherMapper;
import com.example.pctol.server.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.pctol.pojo.entity.Teacher;
/**
 * @author hp
 * @date 2024/3/24
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;
    @Override
    public Result login(LoginVO loginInfo) {
        Teacher teacher=teacherMapper.login(loginInfo.getAccount());
        //账号不存在
        if (teacher==null)
            return new Result(StateCode.ACCOUNT_NOT_EXIST,"账号不存在");

        if(loginInfo.getPassword().equals(teacher.getPassword())){
            return new Result(StateCode.SUCCESS,"登入成功",teacher);
        }else //密码错误
            return new Result(StateCode.PASSWORD_ERROR,"密码错误");
    }
}
