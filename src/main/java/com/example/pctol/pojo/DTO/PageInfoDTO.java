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
public class PageInfoDTO {
    private Integer page;
    private Integer pageSize;

    public int getStart() {
        return (page-1)*pageSize;
    }
}
