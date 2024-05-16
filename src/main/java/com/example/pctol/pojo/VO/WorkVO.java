package com.example.pctol.pojo.VO;

import com.example.pctol.pojo.entity.Homework;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hp
 * @date 2024/5/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkVO extends Homework {
    private String SubjectName;

    public WorkVO(Homework homework){
        super(homework);
    }
}
