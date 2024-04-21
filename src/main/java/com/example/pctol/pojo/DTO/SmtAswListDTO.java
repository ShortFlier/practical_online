package com.example.pctol.pojo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用于接受交卷的每个题目答案
 * @author hp
 * @date 2024/4/21
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SmtAswListDTO {
    private Integer type;
    private Long topicId;
    private String submitAnswer;
}
