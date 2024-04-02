package com.example.pctol.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author hp
 * @date 2024/4/2
 */

public class ExcelFormatException extends Exception{
    public ExcelFormatException(String msg){
        super(msg);
    }
}
