package com.example.pctol.pojo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hp
 * @date 2024/4/16
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaperSearchDTO {
    private Long subjectId;
    private Character difficulty;
    private String launcher;
    private String title;

    private Character display;
    private PageInfo pageInfo;

    public void difCheck(){
        if(difficulty!=null)
            if(difficulty<'1'||difficulty>'5')
                difficulty=null;
    }

    public Integer getStart(){
        if(pageInfo==null)
            return null;
        return pageInfo.getStart();
    }

    public Integer getPageSize(){
        if(pageInfo==null)
            return null;
        return pageInfo.getPageSize();
    }
}
