package com.example.pctol.pojo.VO;

import com.example.pctol.server.annotation.AutoFill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author hp
 * @date 2024/3/27
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageResult {
    private int total;
    private List rows;
}
