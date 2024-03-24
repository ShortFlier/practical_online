package com.example.pctol.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 学生表全字段
 *
 * @author hp
 * @date 2024/3/24
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    private Long stuId;
    private String account;
    private String name;
    private String password;
    private String email;
    private String phone;
    private LocalDateTime create_time;
    private LocalDateTime update_time;
}
