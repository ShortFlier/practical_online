package com.example.pctol.pojo.entity;

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
}
