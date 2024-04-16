package com.example.pctol.pojo.VO;

import com.example.pctol.pojo.entity.Paper;
import lombok.Data;

/**
 * @author hp
 * @date 2024/4/16
 */
@Data
public class PaperVO extends Paper{
    private String subjectName;

    public PaperVO(Paper paper,String subjectName){
        super(paper);
        this.subjectName=subjectName;
    }
}
