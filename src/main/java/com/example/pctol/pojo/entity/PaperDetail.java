package com.example.pctol.pojo.entity;

import com.example.pctol.common.constant.TopicConstant;
import com.example.pctol.common.utils.Util;
import com.example.pctol.pojo.DTO.TestRdmDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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

    public PaperDetail(TestRdmDTO testRdmDTO, Long id) {
        paperId=id;
        radioMarks= testRdmDTO.getTopicNumbers().get(0);
        mulMarks= testRdmDTO.getTopicNumbers().get(1);
        judgMarks= testRdmDTO.getTopicNumbers().get(2);
        fitbMarks= testRdmDTO.getTopicNumbers().get(3);
        vocMarks= testRdmDTO.getTopicNumbers().get(4);
    }

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

    public int getMask(int type){
        switch (type){
            case TopicConstant.RADIOES:
                return getRadioMarks();
            case TopicConstant.MULTIPLE_CHOICES:
                return getMulMarks();
            case TopicConstant.JUDGMENT:
                return getJudgMarks();
            case TopicConstant.FILL_IN_THE_BLANK:
                return getFitbMarks();
            case TopicConstant.VOCABULARY_QST:
                return getVocMarks();
            default: return 0;
        }
    }

    public void setIds(int type, List<Integer> list){
        switch (type){
            case TopicConstant.RADIOES:
                radioIds= Util.idArrToStr(list);break;
            case TopicConstant.MULTIPLE_CHOICES:
                mulIds= Util.idArrToStr(list);break;
            case TopicConstant.JUDGMENT:
                judgIds= Util.idArrToStr(list);break;
            case TopicConstant.FILL_IN_THE_BLANK:
                fitbIds= Util.idArrToStr(list);break;
            case TopicConstant.VOCABULARY_QST:
                vocIds= Util.idArrToStr(list);break;
            default: ;
        }
    }

}
