package com.example.pctol.pojo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hp
 * @date 2024/4/2
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TopicSearchInfoDTO {
    private Integer type;
    private Integer id;
    private String question;
    private String launcher;
    private Integer auditState;
    private Integer subjectId;
    private Character difficulty;
    private Integer page;
    private Integer pageSize;
    private Integer start;

    public void cptStart(){
        start=(page-1)*pageSize;
    }
}
