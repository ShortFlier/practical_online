package com.example.pctol.pojo.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author hp
 * @date 2024/5/15
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberVO {
    private Long stuId;
    private String name;
    private LocalDateTime createTime;
    private Long teamId;
}
