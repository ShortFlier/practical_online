package com.example.pctol.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 成员表
 *
 * @author hp
 * @date 2024/5/15
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Members {
    private Long teamId;
    private Long stuId;
    private LocalDateTime createTime;
}
