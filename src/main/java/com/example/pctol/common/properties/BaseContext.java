package com.example.pctol.common.properties;

public class BaseContext {

    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void setLoginInfo(String loginInfo) {
        threadLocal.set(loginInfo);
    }

    public static String getLoginInfo() {
        return threadLocal.get();
    }

    public static void removeCurrentId() {
        threadLocal.remove();
    }

}
