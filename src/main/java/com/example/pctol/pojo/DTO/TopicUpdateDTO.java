package com.example.pctol.pojo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author hp
 * @date 2024/4/9
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TopicUpdateDTO {
    private Integer type;
    private Long id;
    private LocalDateTime updateTime;
    private Integer auditState;
    private LocalDateTime auditTime;
    private Long subjectId;
    private Character difficulty;
}
