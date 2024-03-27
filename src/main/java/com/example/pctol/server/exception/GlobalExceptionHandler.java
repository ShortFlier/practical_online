package com.example.pctol.server.exception;


import com.example.pctol.common.constant.StateCode;
import com.example.pctol.pojo.VO.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result ex(Exception ex){
        Throwable lastException = ex;
        while (lastException.getCause() != null) {
            lastException = lastException.getCause();
        }
        return new Result(StateCode.FAILED, lastException.getMessage());
    }
}
