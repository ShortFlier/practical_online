package com.example.pctol.server.annotation;

import com.example.pctol.common.constant.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hp
 * @date 2023/8/2
 */
//自定义注解，用于填充公共字段
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoFill {
    //操作类型：insert,update
    OperationType value();
}
