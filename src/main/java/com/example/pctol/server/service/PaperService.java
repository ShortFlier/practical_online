package com.example.pctol.server.service;

import com.example.pctol.pojo.DTO.PaperSearchDTO;
import com.example.pctol.pojo.VO.Result;

/**
 * @author hp
 * @date 2024/4/15
 */
public interface PaperService {
    Result gets(PaperSearchDTO paperSearchDTO);

    Result getByIdNt(long id);
}
