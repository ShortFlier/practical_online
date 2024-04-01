package com.example.pctol.pojo.entity;

import com.example.pctol.common.constant.AuditState;
import com.example.pctol.common.properties.BaseContext;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 单选题全字段
 *
 * @author hp
 * @date 2024/3/24
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Radioes {
    private Long id;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private char answer;
    private String analyse;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String launcher;
    private Integer auditState;
    private LocalDateTime auditTime;
    private Long subjectId;
    private char difficulty;

    //为excel而备的构造函数
    public Radioes(String question,char answer,String analyse,char difficulty,String optionA,String optionB,String optionC,String optionD){
        this.question=question;
        this.answer=answer;
        this.analyse=analyse;
        this.difficulty=difficulty;
        this.optionA=optionA;
        this.optionB=optionB;
        this.optionC=optionC;
        this.optionD=optionD;
    }

    private void setPublic(){
        createTime=updateTime=LocalDateTime.now();
        launcher= BaseContext.getLoginInfo();
        auditState= AuditState.AWAIT;
    }

    //根据excel读取的内容，返回完整合法的类集合
    public static <T> void getList(List<T> cachedDataList) {

    }

}

