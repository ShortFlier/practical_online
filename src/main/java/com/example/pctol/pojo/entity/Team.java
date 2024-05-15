package com.example.pctol.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 学习小组表
 *
 * @author hp
 * @date 2024/5/15
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Team {
    private Long id;
    private String name;
    private String description;
    private Long thId;
    private String invitationCode;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
