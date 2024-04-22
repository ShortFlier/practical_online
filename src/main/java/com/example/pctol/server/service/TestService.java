package com.example.pctol.server.service;

import com.example.pctol.pojo.DTO.StuTestHisDTO;
import com.example.pctol.pojo.VO.Result;

/**
 * @author hp
 * @date 2024/4/22
 */
public interface TestService {
    Result getHis(StuTestHisDTO stuTestHisDTO);

    Result testLook(Long testId, boolean eraseAnswer);
}
