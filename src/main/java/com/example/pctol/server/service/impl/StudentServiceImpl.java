package com.example.pctol.server.service.impl;

import com.example.pctol.common.constant.MsgConstant;
import com.example.pctol.common.constant.StateCode;
import com.example.pctol.common.properties.JWTproperties;
import com.example.pctol.pojo.DTO.LoginDTO;
import com.example.pctol.pojo.DTO.StuSearchDTO;
import com.example.pctol.pojo.VO.PageResult;
import com.example.pctol.pojo.VO.Result;
import com.example.pctol.pojo.entity.Student;
import com.example.pctol.server.mapper.StudentMapper;
import com.example.pctol.server.service.StudentService;
import com.example.pctol.common.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.pctol.common.constant.JwtClaimsConstant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hp
 * @date 2024/3/24
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private JWTproperties jwtProperties;
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public Result login(LoginDTO loginInfo) {
        Student student=studentMapper.login(loginInfo.getAccount());
        //账号不存在
        if (student==null)
            return new Result(StateCode.ACCOUNT_NOT_EXIST,"账号不存在");

        if(loginInfo.getPassword().equals(student.getPassword())){
            //登录成功后，生成jwt令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put(JwtClaimsConstant.STUDENT_ID,"[student]:"+student.getStuId());
            String token = JwtUtil.createJWT(
                    jwtProperties.getStudentSecretKey(),
                    jwtProperties.getStudentTtl(),
                    claims);
            student.setPassword(null);
            return new Result(StateCode.SUCCESS,token, student);
        }else //密码错误
            return new Result(StateCode.PASSWORD_ERROR,"密码错误");
    }

    @Override
    public Result pageGet(StuSearchDTO stuSearchDTO) throws Exception {
        int total=studentMapper.getAct(stuSearchDTO);
        if(total==0)
            throw new Exception(MsgConstant.NO_DATA);
        List<Student> list=studentMapper.pageGet(stuSearchDTO,stuSearchDTO.getStart(),stuSearchDTO.getPageSize());
        PageResult pageResult=new PageResult(total,list);
        return Result.success(pageResult);
    }

    @Override
    public Result getAct() throws Exception {
        int total=studentMapper.getAct(new StuSearchDTO());
        if(total==0)
            throw new Exception(MsgConstant.NO_DATA);
        return Result.success(total);
    }
}
