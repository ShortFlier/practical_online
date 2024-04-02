package com.example.pctol.server.exception;


import com.example.pctol.common.constant.StateCode;
import com.example.pctol.common.exception.ExcelFormatException;
import com.example.pctol.pojo.VO.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result ex(Exception ex){
        Throwable lastException = ex;
        ExcelFormatException excelException = null;

        // 找到最后一个异常
        while (lastException.getCause() != null) {
            if (lastException instanceof ExcelFormatException) {
                excelException = (ExcelFormatException) lastException;
                break;
            }
            lastException = lastException.getCause();
        }

        if (excelException != null) {
            log.error("ExcelFormatException occurred: " + excelException.getMessage());
            return new Result(StateCode.SUCCESS, excelException.getMessage());
        } else {
            log.error("Case:" + ex.getMessage());
            return new Result(StateCode.FAILED, ex.getMessage());
        }
    }


}
