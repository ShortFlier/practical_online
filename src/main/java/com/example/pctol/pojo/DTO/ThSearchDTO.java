package com.example.pctol.pojo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hp
 * @date 2024/3/28
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ThSearchDTO {
    private String account;
    private String name;
    private PageInfoDTO pageInfoDTO;

    public int getPageSize(){
        return pageInfoDTO.getPageSize();
    }

    public int getStart(){
        return pageInfoDTO.getStart();
    }
}
