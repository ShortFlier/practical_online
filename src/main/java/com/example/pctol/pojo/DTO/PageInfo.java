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
public class PageInfo {
    private Integer page;
    private Integer pageSize;

    private Integer start;

    public void setStart(){
        start=(page-1)*pageSize;
    }

    public int getStart() {
        return (page-1)*pageSize;
    }
}
