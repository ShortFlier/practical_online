package com.example.pctol.pojo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hp
 * @date 2024/4/22
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StuTestHisDTO {
    private Long studentId;
    private Long subjectId;

    private Integer page;
    private Integer pageSize;

    private Integer start;

    public void setStart(){
        start= (page-1)*pageSize;
    }
}
