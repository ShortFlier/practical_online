package com.example.pctol.pojo.VO;

import com.example.pctol.server.annotation.AutoFill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 已在数据库的合法学科信息
 *
 * @author hp
 * @date 2024/3/27
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubInfoVo {
    private Integer id;
    private String name;
    private LocalDateTime createTime;
    private String launcher;
    private int topicNumber;

    private int radioesNumber;
    private int judgmentNumber;
    private int mChoicesNumber;
    private int fitbNumber;
    private int vocaNumber;

    public SubInfoVo computeTopicNumber(){
        topicNumber=radioesNumber+judgmentNumber+mChoicesNumber+fitbNumber+vocaNumber;
        return this;
    }
}
