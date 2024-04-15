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

/**
 * 判断题表全字段
 *
 * @author hp
 * @date 2024/3/24
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Judgment extends Topic{
    private Long id;
    private String question;
    private char answer;
    private String analyse;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String launcher;
    private Integer auditState;
    private LocalDateTime auditTime;
    private Long subjectId;
    private Character difficulty;

    //为excel而备的构造函数
    public Judgment(String question,char answer,String analyse,Character difficulty,Long subjectId) throws ExcelFormatException {
        this.question=question;
        answer=Character.toLowerCase(answer);
        if(TopicConstant.JUDGMENT_ANSWER_LIST.contains(String.valueOf(answer)))
            this.answer=answer;
        else
            throw new ExcelFormatException(ExcelConstant.JUDGMENT_ANSWER_ERROR);
        this.analyse=analyse;
        if(difficulty!=null&& TopicConstant.DIFFICULTY_LIST.contains(String.valueOf(difficulty)))
            this.difficulty=difficulty;
        else
            this.difficulty=null;
        this.subjectId=subjectId;
    }

    public Judgment setPublic(){
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
                if(linkedHashMap.size()!= ExcelConstant.ELSE_COLUMN||((String) linkedHashMap.get(1)).length()>1)
                    throw new ExcelFormatException(BaseContext.getLoginInfo()+"#n*"+ DataListener.BATCH_COUNT +"+"+i+"#"+ExcelConstant.FAILED_TYPE+ linkedHashMap);
                Character answer = (linkedHashMap.get(1) != null) ? ((String) linkedHashMap.get(1)).charAt(0) : null;
                Character difficulty = (linkedHashMap.get(3) != null) ? ((String) linkedHashMap.get(3)).charAt(0) : null;

                list.add(new Judgment((String) linkedHashMap.get(0), answer,
                        (String)linkedHashMap.get(2) , difficulty,Long.parseLong((String) linkedHashMap.get(4))).setPublic());
            }
            return list;
        }else
            return null;
    }

}
