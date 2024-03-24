package com.example.pctol.server.service.impl;

import com.example.pctol.common.constant.StateCode;
import com.example.pctol.pojo.DTO.LoginVO;
import com.example.pctol.pojo.VO.Result;
import com.example.pctol.pojo.entity.Student;
import com.example.pctol.server.mapper.StudentMapper;
import com.example.pctol.server.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hp
 * @date 2024/3/24
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public Result login(LoginVO loginInfo) {
        Student student=studentMapper.login(loginInfo.getAccount());
        //账号不存在
        if (student==null)
            return new Result(StateCode.ACCOUNT_NOT_EXIST,"账号不存在");

        if(loginInfo.getPassword().equals(student.getPassword())){
            return new Result(StateCode.SUCCESS,"登入成功", student);
        }else //密码错误
            return new Result(StateCode.PASSWORD_ERROR,"密码错误");
    }
}
