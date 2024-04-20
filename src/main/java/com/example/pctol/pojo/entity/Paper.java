package com.example.pctol.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author hp
 * @date 2024/4/13
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Paper {
    private Long id;
    private String title;   //标题
    private String description;     //描述
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer duration;      //考试时间--分钟
    private String launcher;
    private Long subjectId;
    private Integer totalMarks;     //试卷分数
    private Character difficulty; // 难度，数字1-5表示
    private Character display; // 0私有，1公开

    public Paper(Paper paper) {
        this.id = paper.getId();
        this.title = paper.getTitle();
        this.description = paper.getDescription();
        this.createTime = paper.getCreateTime();
        this.updateTime = paper.getUpdateTime();
        this.duration = paper.getDuration();
        this.launcher = paper.getLauncher();
        this.subjectId = paper.getSubjectId();
        this.totalMarks = paper.getTotalMarks();
        this.difficulty = paper.getDifficulty();
        this.display=paper.getDisplay();
    }
}
