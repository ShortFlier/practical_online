package com.example.pctol.pojo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hp
 * @date 2024/4/23
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TestRdmDTO {
    private Integer id;
    private List<Integer> topicNumbers;
    private char difficulty;
    private List<Integer> topicMarks;
    private long subjectId;
    private int totalMarks;
    private char display;
    private String title;
    private String description;
    private int duration;
    private String launcher;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
