package com.example.pctol.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 学科表全字段
 *
 * @author hp
 * @date 2024/3/24
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Subject {
    public Long id;
    public String name;
    public LocalDateTime create_time;
    public LocalDateTime update_time;
//    创建者
    public String launcher;
//    审核状态
    public Integer auditState;
//    审核日期
    public LocalDateTime auditTime;
}
