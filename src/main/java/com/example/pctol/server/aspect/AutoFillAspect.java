package com.example.pctol.server.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import com.example.pctol.server.annotation.AutoFill;
import com.example.pctol.common.constant.OperationType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import com.example.pctol.common.constant.AutoFillConstant;
/**
 * 自定义切面类,实现公共字段填充
 *
 * @author hp
 * @date 2023/8/2
 */
@Aspect
@Component
@Slf4j
public class AutoFillAspect {

    //切入点
    @Pointcut("execution(* com.example.pctol.server.mapper.*.*(..))&&@annotation(com.example.pctol.server.annotation.AutoFill)")
    public void autoFillPointCut(){}


    //操作
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint){
        log.info("填充自定义字段");

        //获取操作类型
        MethodSignature signature= (MethodSignature) joinPoint.getSignature();
        AutoFill autoFill=signature.getMethod().getAnnotation(AutoFill.class);
        OperationType operationType=autoFill.value();

        //获取实体类参数
        Object[] args=joinPoint.getArgs();
        if(args==null||args.length==0){
            return;
        }
        Object entity=args[0];

        //数据赋值
        LocalDateTime now=LocalDateTime.now();
//       Long currentId= BaseContext.getCurrentId();
        if(operationType==OperationType.INSERT){
            try {
                //获取方法
                Method setCreateTime=entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME,LocalDateTime.class);
                Method setUpdateTime=entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
//                Method setCreateUser=entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER,Long.class);
//                Method setUpdateUser=entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                //赋值
                setCreateTime.invoke(entity,now);
//                setCreateUser.invoke(entity,currentId);
                setUpdateTime.invoke(entity,now);
//                setUpdateUser.invoke(entity,currentId);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }else  if(operationType==OperationType.UPDATE){

            try{
                //获取方法
                Method setUpdateTime=entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
//                Method setUpdateUser=entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                //赋值
                setUpdateTime.invoke(entity,now);
//                setUpdateUser.invoke(entity,currentId);
            }catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
