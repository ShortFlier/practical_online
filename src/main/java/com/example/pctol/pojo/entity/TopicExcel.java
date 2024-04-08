package com.example.pctol.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author hp
 * @date 2024/4/1
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TopicExcel {
    private Integer id;
    private String name;
    private String url;
    private String launcher;
    private LocalDateTime create_time;
    private Integer errorMsg;
}
