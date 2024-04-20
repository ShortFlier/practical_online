package com.example.pctol.server.service;

import com.example.pctol.pojo.DTO.PageInfo;
import com.example.pctol.pojo.DTO.SubSearchDTO;
import com.example.pctol.pojo.DTO.SubUpdateInfoDTO;
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

    void dle(String name) throws Exception;

    void update(SubUpdateInfoDTO subUpdateInfoDTO) throws Exception;

    Result getByAudit(Integer auditState, Integer page, Integer pageSize);

    Result getList();

    Result thGet(PageInfo pageInfo);
}
