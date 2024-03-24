package com.example.pctol.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 教师全字段表
 *
 * @author hp
 * @date 2024/3/24
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Teacher {
    private Long thId;
    private String account;
    private String name;
    private String password;
    private String email;
    private String phone;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
