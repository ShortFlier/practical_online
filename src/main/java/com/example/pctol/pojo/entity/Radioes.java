package com.example.pctol.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 单选题全字段
 *
 * @author hp
 * @date 2024/3/24
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Radioes {
    private Long id;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private char answer;
    private String analyse;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String launcher;
    private Integer auditState;
    private LocalDateTime auditTime;
    private Long subjectId;
    private char difficulty;
}

