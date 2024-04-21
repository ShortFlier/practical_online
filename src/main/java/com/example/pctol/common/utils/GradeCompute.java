package com.example.pctol.common.utils;

import com.example.pctol.pojo.entity.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hp
 * @date 2024/4/12
 */
@Slf4j
public class GradeCompute {
    public static void radioesGrade(Practice practice, Radioes radioes){
        if(practice.getSubmitAnswer()==null){
            practice.setGrade(0);
            return;
        }
        log.info("标准答案：{}\n用户答案：{}",radioes.getAnswer(),practice.getSubmitAnswer());
        if(practice.getSubmitAnswer().equals(String.valueOf(radioes.getAnswer()))){
            practice.setGrade(100);
        }else {
            practice.setGrade(0);
        }
    }

    public static void radioesGrade(PaperTopic paperTopic, Radioes radioes){
        Practice practice=new Practice();
        practice.setSubmitAnswer(paperTopic.getSubmitAnswer());
        radioesGrade(practice,radioes);
        paperTopic.setGrade(paperTopic.getGrade());
    }

    public static void mulChoGrade(Practice practice, MultipleChoices multipleChoices){
        if(practice.getSubmitAnswer()==null){
            practice.setGrade(0);
            return;
        }
        log.info("标准答案：{}\n用户答案：{}",multipleChoices.getAnswer(),practice.getSubmitAnswer());
        if(practice.getSubmitAnswer().equals(multipleChoices.getAnswer())){
            practice.setGrade(100);
        }else if(multipleChoices.getAnswer().contains(practice.getSubmitAnswer())){
            practice.setGrade(50);
        }else
            practice.setGrade(0);
    }

    public static void mulChoGrade(PaperTopic paperTopic, MultipleChoices multipleChoices){
        Practice practice=new Practice();
        practice.setSubmitAnswer(paperTopic.getSubmitAnswer());
        mulChoGrade(practice,multipleChoices);
        paperTopic.setGrade(paperTopic.getGrade());
    }

    public static void judgeGrade(Practice practice, Judgment judgment){
        log.info("标准答案：{}\n用户答案：{}",judgment.getAnswer(),practice.getSubmitAnswer());
        if(practice.getSubmitAnswer().equals(String.valueOf(judgment.getAnswer()))){
            practice.setGrade(100);
        }else {
            practice.setGrade(0);
        }
    }

    public static void judgeGrade(PaperTopic paperTopic, Judgment judgment){
        Practice practice=new Practice();
        practice.setSubmitAnswer(paperTopic.getSubmitAnswer());
        judgeGrade(practice,judgment);
        paperTopic.setGrade(paperTopic.getGrade());
    }
}
