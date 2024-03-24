package com.example.pctol.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 管理员表字段
 *
 * @author hp
 * @date 2024/3/24
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Admin {
    private String account;
    private String name;
    private String password;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
