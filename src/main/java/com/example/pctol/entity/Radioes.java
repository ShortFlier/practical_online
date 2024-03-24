package com.example.pctol.entity;

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
    public Long id;
    public String question;
    public String optionA;
    public String optionB;
    public String optionC;
    public String optionD;
    public char answer;
    public String analyse;
    public LocalDateTime createTime;
    public LocalDateTime updateTime;
    public String launcher;
    public Integer auditState;
    public LocalDateTime auditTime;
    public Long subjectId;
    public char difficulty;
}

