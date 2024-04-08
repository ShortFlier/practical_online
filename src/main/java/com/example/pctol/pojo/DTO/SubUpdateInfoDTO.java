package com.example.pctol.pojo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author hp
 * @date 2024/3/29
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubUpdateInfoDTO {
    Integer id;
    String name;
    LocalDateTime updateTime;
    Integer auditState;
    LocalDateTime auditTime;


}
