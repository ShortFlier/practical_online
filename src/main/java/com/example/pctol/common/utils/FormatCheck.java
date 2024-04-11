package com.example.pctol.common.utils;

import com.example.pctol.common.constant.MsgConstant;
import com.example.pctol.common.constant.TopicConstant;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

/**
 * @author hp
 * @date 2024/4/10
 */
public class FormatCheck {
    public static void checkAccount(String account) throws Exception {
        if(account==null)
            throw new Exception(MsgConstant.ACCOUNT_FORMAT_ERROR);
        if (!account.matches("^[a-zA-Z0-9_]+$"))
            throw new Exception(MsgConstant.ACCOUNT_FORMAT_ERROR);
    }

    public static void checkPsd(String password) throws Exception {
        if(password==null)
            throw new Exception(MsgConstant.PASSWORD_FORMAT_ERROR);
        if (!password.matches("^[a-zA-Z0-9_]+$"))
            throw new Exception(MsgConstant.PASSWORD_FORMAT_ERROR);
    }

    public static void checkEmail(String email) throws Exception {
        if(email==null)
            throw new Exception(MsgConstant.EMAIL_FORMAT_ERROR);
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))
            throw new Exception(MsgConstant.EMAIL_FORMAT_ERROR);
    }
    //题目类型type检查
    public static boolean checkTopicType(Integer type){
        if(type==null)
            return false;
        if(type<(TopicConstant.RADIOES-1)&type>(TopicConstant.VOCABULARY_QST+1))
            return false;
        return true;
    }

    //难度difficulty检查
    public static boolean checkDifficulty(Character difficulty){
        if(difficulty == null || difficulty < '1' || difficulty > '5') {
            return false;
        } else {
            return true;
        }
    }
}
