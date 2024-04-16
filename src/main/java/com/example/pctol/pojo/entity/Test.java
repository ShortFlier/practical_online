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
public class Test {
    private Long id;
    private Long paperId;
    private String launcher;
    private Long studentId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer grade;
}
