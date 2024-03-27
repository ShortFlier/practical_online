package com.example.pctol.pojo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hp
 * @date 2024/3/27
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubSearchDTO {
    private String key;
    private PageInfo pageInfo;

    public int getPageSize(){
        return pageInfo.getPageSize();
    }

    public int getStart(){
        return pageInfo.getStart();
    }
}
