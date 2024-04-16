package com.example.pctol.pojo.entity;

import com.example.pctol.common.constant.TopicConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author hp
 * @date 2024/4/13
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaperDetail {
    private Long paperId; // 对应试卷id

    private Integer radioMarks; // 单选总分数
    private String radioIds; // 单选题id列表，使用“#id#”分隔

    private Integer mulMarks; // 多选总分数
    private String mulIds; // 多选题id，使用“#id#”分隔

    private Integer judgMarks; // 判断题总分
    private String judgIds; // 判断题id，使用“#id#”分隔

    private Integer fitbMarks; // 填空题总分
    private String fitbIds; // 填空题id，使用“#id#”分隔

    private Integer vocMarks; // 应用题总分
    private String vocIds; // 应用题id，使用“#id#”分隔

    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间

    public String getIds(int type){
        switch (type){
            case TopicConstant.RADIOES:
                return getRadioIds();
            case TopicConstant.MULTIPLE_CHOICES:
                return getMulIds();
            case TopicConstant.JUDGMENT:
                return getJudgIds();
            case TopicConstant.FILL_IN_THE_BLANK:
                return getFitbIds();
            case TopicConstant.VOCABULARY_QST:
                return getVocIds();
            default: return null;
        }
    }

}
