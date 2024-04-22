package com.example.pctol.pojo.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author hp
 * @date 2024/4/22
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TestVO {
    private String title;
    private Long id;    //test_id
    private Integer grade;
    private LocalDateTime createTime; //考试时间

    private String subjectName;
    private Long subjectId;
}
