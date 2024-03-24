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
    public Long stuId;
    public String account;
    public String name;
    public String password;
    public String email;
    public String phone;
    public LocalDateTime create_time;
    public LocalDateTime update_time;
}
