package com.example.pctol.pojo.entity;

import com.example.pctol.common.constant.AuditState;
import com.example.pctol.common.constant.ExcelConstant;
import com.example.pctol.common.constant.TopicConstant;
import com.example.pctol.common.exception.ExcelFormatException;
import com.example.pctol.common.properties.BaseContext;
import com.example.pctol.common.utils.DataListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 多选题全字段
 *
 * @author hp
 * @date 2024/3/24
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MultipleChoices {
    private Long id;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String optionE;
    private String optionF;
    private String answer;
    private String analyse;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String launcher;
    private Integer auditState;
    private LocalDateTime auditTime;
    private Long subjectId;
    private Character difficulty;


    //为excel而备的构造函数
    public MultipleChoices(String question,String answer,String analyse,Character difficulty,String optionA,String optionB,String optionC,String optionD,String optionE,String optionF) throws ExcelFormatException {
        this.question=question;
        answer=answer.toLowerCase();
        if(answer!=null) {
            this.answer = answer;
        }
        else
            throw new ExcelFormatException(ExcelConstant.NULL_ANSWER);
        this.analyse=analyse;
        if(difficulty!=null&&TopicConstant.DIFFICULTY_LIST.contains(String.valueOf(difficulty)))
            this.difficulty=difficulty;
        else
            this.difficulty=null;
        this.optionA=optionA;
        this.optionB=optionB;
        this.optionC=optionC;
        this.optionD=optionD;
        this.optionE=optionE;
        this.optionF=optionF;
    }

    public MultipleChoices setPublic() throws ExcelFormatException {
        // 将字符串转换为小写，去除重复字符，排序
        String sortedStr = answer.toLowerCase()
                .chars()
                .distinct()
                .sorted()
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());
        // 检查是否是"abcdef"的子串
        if(!sortedStr.chars().allMatch(c -> TopicConstant.MULTIPLE_CHOICES_ANSWER_LIST.contains(String.valueOf((char) c))))
            throw new ExcelFormatException(ExcelConstant.MULTIPLE_CHOICES_ANSWER_ERROR);
        createTime=updateTime=LocalDateTime.now();
        launcher= BaseContext.getLoginInfo().split("#")[1];
        auditState= AuditState.AWAIT;
        return this;
    }

    //根据excel读取的内容，返回完整合法的类集合
    public static <T> List getList(List<T> cachedDataList) throws ExcelFormatException {
        if(!cachedDataList.isEmpty()){
            List list=new ArrayList<>();
            LinkedHashMap linkedHashMap;
            for (int i = 0; i < cachedDataList.size(); i++) {
                //如果列数不正确，说明类型不对
                linkedHashMap= (LinkedHashMap) cachedDataList.get(i);
                if(linkedHashMap.size()!= ExcelConstant.MULCHO_COLUMN)
                    throw new ExcelFormatException(BaseContext.getLoginInfo()+"#n*"+ DataListener.BATCH_COUNT +"+"+i+"#"+ExcelConstant.FAILED_TYPE+ linkedHashMap);
                Character difficulty = (linkedHashMap.get(3) != null) ? ((String) linkedHashMap.get(3)).charAt(0) : null;

                list.add(new MultipleChoices((String) linkedHashMap.get(0), (String) linkedHashMap.get(1),
                        (String)linkedHashMap.get(2) , difficulty,
                        (String) linkedHashMap.get(4), (String) linkedHashMap.get(5),
                        (String) linkedHashMap.get(6), (String) linkedHashMap.get(7),
                        (String) linkedHashMap.get(8),(String) linkedHashMap.get(9)).setPublic());
            }
            return list;
        }else
            return null;
    }
}
