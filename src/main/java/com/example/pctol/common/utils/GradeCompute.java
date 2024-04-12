package com.example.pctol.common.utils;

import com.example.pctol.pojo.entity.Judgment;
import com.example.pctol.pojo.entity.MultipleChoices;
import com.example.pctol.pojo.entity.Practice;
import com.example.pctol.pojo.entity.Radioes;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hp
 * @date 2024/4/12
 */
@Slf4j
public class GradeCompute {
    public static void radioesGrade(Practice practice, Radioes radioes){
        log.info("标准答案：{}\n用户答案：{}",radioes.getAnswer(),practice.getSubmitAnswer());
        if(practice.getSubmitAnswer().equals(String.valueOf(radioes.getAnswer()))){
            practice.setGrade(100);
        }else {
            practice.setGrade(0);
        }
    }
    public static void mulChoGrade(Practice practice, MultipleChoices multipleChoices){
        log.info("标准答案：{}\n用户答案：{}",multipleChoices.getAnswer(),practice.getSubmitAnswer());
        if(practice.getSubmitAnswer().equals(multipleChoices.getAnswer())){
            practice.setGrade(100);
        }else if(multipleChoices.getAnswer().contains(practice.getSubmitAnswer())){
            practice.setGrade(50);
        }else
            practice.setGrade(0);
    }

    public static void judgeGrade(Practice practice, Judgment judgment){
        log.info("标准答案：{}\n用户答案：{}",judgment.getAnswer(),practice.getSubmitAnswer());
        if(practice.getSubmitAnswer().equals(String.valueOf(judgment.getAnswer()))){
            practice.setGrade(100);
        }else {
            practice.setGrade(0);
        }
    }
}
