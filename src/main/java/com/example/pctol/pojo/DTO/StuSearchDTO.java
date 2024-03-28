package com.example.pctol.pojo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.SecureRandom;

/**
 * @author hp
 * @date 2024/3/28
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StuSearchDTO {
    private String account;
    private String name;
    private PageInfo pageInfo;
    public int getStart(){
        return pageInfo.getStart();
    }
    public int getPageSize(){
        return pageInfo.getPageSize();
    }
}
