package com.example.pctol.pojo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 交卷信息
 *
 * @author hp
 * @date 2024/4/21
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SmtPaperDTO {
    private Long paperId;
    private Long submitId;

    private LocalDateTime createTime;

    private List<SmtAswListDTO> smtAswListDTOList;
}
