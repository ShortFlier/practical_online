package com.example.pctol.server.service;

import com.example.pctol.pojo.DTO.LoginDTO;
import com.example.pctol.pojo.DTO.StuSearchDTO;
import com.example.pctol.pojo.VO.Result;
import com.example.pctol.pojo.entity.Student;

/**
 * @author hp
 * @date 2024/3/24
 */
public interface StudentService {
    Result login(LoginDTO loginInfo);

    Result pageGet(StuSearchDTO stuSearchDTO) throws Exception;

    Result getAct() throws Exception;

    Result getInfoByAct(String account);

    Result regist(Student student) throws Exception;

    Student getById(long id);
}
