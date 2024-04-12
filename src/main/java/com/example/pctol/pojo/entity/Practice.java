package com.example.pctol.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author hp
 * @date 2024/4/12
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Practice {
    private Long id;
    private Integer type;
    private Long topicId;
    private Long submitId;
    private String submitAnswer;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer grade;
}
