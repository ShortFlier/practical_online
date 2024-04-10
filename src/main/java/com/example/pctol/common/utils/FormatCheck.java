package com.example.pctol.common.utils;

import com.example.pctol.common.constant.MsgConstant;

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
}
