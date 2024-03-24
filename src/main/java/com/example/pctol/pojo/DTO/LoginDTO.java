package com.example.pctol.pojo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hp
 * @date 2024/3/24
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDTO {
    private String account;
    private String password;
}
