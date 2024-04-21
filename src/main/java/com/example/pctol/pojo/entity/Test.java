package com.example.pctol.pojo.entity;

import com.example.pctol.common.properties.BaseContext;
import com.example.pctol.pojo.DTO.SmtPaperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author hp
 * @date 2024/4/16
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Test {
    private Long id;
    private Long paperId;
    private String launcher;
    private Long studentId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer grade;

    public Test(SmtPaperDTO smtPaperDTO){
        paperId=smtPaperDTO.getPaperId();
        studentId= smtPaperDTO.getSubmitId();
        createTime=smtPaperDTO.getCreateTime();
        updateTime=LocalDateTime.now();
        launcher= BaseContext.getLoginInfo();
    }
}
