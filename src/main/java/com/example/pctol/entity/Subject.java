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
    private Long id;
    private String name;
    private LocalDateTime create_time;
    private LocalDateTime update_time;
//    创建者
    private String launcher;
//    审核状态
    private Integer auditState;
//    审核日期
    private LocalDateTime auditTime;
}
