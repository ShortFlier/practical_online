package com.example.pctol.entity;

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
    public String account;
    public String name;
    public String password;
    public LocalDateTime create_time;
    public LocalDateTime update_time;
}
