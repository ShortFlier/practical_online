package com.example.pctol.server.service;

import com.example.pctol.pojo.DTO.LoginDTO;
import com.example.pctol.pojo.DTO.StuSearchDTO;
import com.example.pctol.pojo.VO.Result;

/**
 * @author hp
 * @date 2024/3/24
 */
public interface StudentService {
    Result login(LoginDTO loginInfo);

    Result pageGet(StuSearchDTO stuSearchDTO) throws Exception;

    Result getAct() throws Exception;
}
