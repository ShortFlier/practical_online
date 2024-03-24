package com.example.pctol.server.service;

import com.example.pctol.pojo.DTO.LoginVO;
import com.example.pctol.pojo.VO.Result;
import com.example.pctol.pojo.entity.Admin;

/**
 * @author hp
 * @date 2024/3/24
 */
public interface AdminService {
    Result login(LoginVO admin);
}
