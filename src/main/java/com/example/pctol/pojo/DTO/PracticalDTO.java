package com.example.pctol.pojo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hp
 * @date 2024/4/11
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PracticalDTO {
    private Long subjectId;
    private Character difficulty;
    //题目类型
    private Integer type;

    public void difCheck(){
        if(difficulty!=null)
            if(difficulty<'1'||difficulty>'5')
                difficulty=null;
    }
}
