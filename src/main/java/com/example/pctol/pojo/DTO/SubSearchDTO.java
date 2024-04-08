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
    private PageInfoDTO pageInfoDTO;

    public int getPageSize(){
        return pageInfoDTO.getPageSize();
    }

    public int getStart(){
        return pageInfoDTO.getStart();
    }
}
