package com.example.pctol.server.service;

import com.example.pctol.pojo.DTO.LoginDTO;
import com.example.pctol.pojo.VO.Result;

/**
 * @author hp
 * @date 2024/3/24
 */
public interface TeacherService {
    Result login(LoginDTO loginInfo);
}
