package com.example.pctol.pojo.entity;

import com.example.pctol.pojo.DTO.SmtAswListDTO;
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
public class PaperTopic {
    private Long id;
    private Long testId;
    private Long paperId;
    private int type;
    private Long topicId;
    private Long submitId;
    private String submitAnswer;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer grade;

    public PaperTopic(SmtPaperDTO smtPaperDTO){
        paperId= smtPaperDTO.getPaperId();
        submitId= smtPaperDTO.getSubmitId();
        createTime= LocalDateTime.now();
        updateTime= LocalDateTime.now();
    }

    public PaperTopic setTopic(SmtAswListDTO smtAswListDTO){
        type=smtAswListDTO.getType();
        topicId=smtAswListDTO.getTopicId();
        submitAnswer=smtAswListDTO.getSubmitAnswer();
        return this;
    }

    public PaperTopic setTestId(Long testId){
        this.testId=testId;
        return this;
    }
}
