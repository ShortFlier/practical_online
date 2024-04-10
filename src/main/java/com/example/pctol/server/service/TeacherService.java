package com.example.pctol.server.service;

import com.example.pctol.pojo.DTO.LoginDTO;
import com.example.pctol.pojo.DTO.ThSearchDTO;
import com.example.pctol.pojo.VO.Result;
import com.example.pctol.pojo.entity.Teacher;

/**
 * @author hp
 * @date 2024/3/24
 */
public interface TeacherService {
    Result login(LoginDTO loginInfo);

    Result pageGet(ThSearchDTO thSearchDTO) throws Exception;

    Integer getAct();

    Result getInfoByAct(String account);

    Result regit(Teacher teacher) throws Exception;
}
