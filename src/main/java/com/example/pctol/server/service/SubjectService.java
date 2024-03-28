package com.example.pctol.server.service;

import com.example.pctol.pojo.DTO.SubSearchDTO;
import com.example.pctol.pojo.VO.PageResult;
import com.example.pctol.pojo.VO.Result;
import com.example.pctol.pojo.entity.Subject;

/**
 * @author hp
 * @date 2024/3/25
 */
public interface SubjectService {
    void add(Subject subject);

    PageResult getData(SubSearchDTO subSearchDTO) throws Exception;

    void adminAdd(Subject subject);

    Result getLikeName(String name);

    void dle(String name);
}
