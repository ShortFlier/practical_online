package com.example.pctol.server.service;

import com.example.pctol.pojo.DTO.PaperSearchDTO;
import com.example.pctol.pojo.DTO.SmtPaperDTO;
import com.example.pctol.pojo.DTO.TestRdmDTO;
import com.example.pctol.pojo.VO.Result;
import com.example.pctol.pojo.entity.Paper;

/**
 * @author hp
 * @date 2024/4/15
 */
public interface PaperService {
    Result gets(PaperSearchDTO paperSearchDTO);

    Result getByIdNt(long id,boolean eraseAnswer);

    Result test(long id);

    void testSubmit(SmtPaperDTO smtPaperDTO);

    Result randomC(TestRdmDTO testRdmDTO) throws Exception;

    Result update(Paper paper);
}
