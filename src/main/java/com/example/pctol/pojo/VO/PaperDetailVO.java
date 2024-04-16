package com.example.pctol.pojo.VO;

import com.example.pctol.pojo.entity.Paper;
import com.example.pctol.pojo.entity.PaperDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * @author hp
 * @date 2024/4/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperDetailVO {
    private Paper paper;

    private Integer radioMarks; // 单选总分数
    private List radioList;

    private Integer mulMarks; // 多选总分数
    private List mulList;

    private Integer judgMarks; // 判断题总分
    private List judgList;

    private Integer fitbMarks; // 填空题总分
    private List fitbList;

    private Integer vocMarks; // 应用题总分
    private List vocList;

    private Character difficulty; // 难度，数字1-5表示

    public PaperDetailVO(Paper paper, PaperDetail paperDetail){
        this.paper=paper;
        radioMarks= paperDetail.getRadioMarks();
        mulMarks= paperDetail.getMulMarks();
        judgMarks= paperDetail.getJudgMarks();
        fitbMarks= paperDetail.getFitbMarks();
        vocMarks= paperDetail.getVocMarks();
        difficulty=paper.getDifficulty();
    }
}
