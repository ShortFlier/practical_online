package com.example.pctol.pojo.VO;

import com.example.pctol.common.constant.StateCode;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 统一返回值
 *
 * @author hp
 * @date 2024/3/24
 */
@AllArgsConstructor
@Data
public class Result {
    private int code;
    private String msg;
    private Object data;

    public Result(int code, String msg){
        this.code=code;
        this.msg=msg;
    }

    public Result(int code, Object data){
        this.code=code;
        this.data=data;
    }

    public Result(int code){
        this.code=code;
    }

    public static Result success(String msg){
        return new Result(StateCode.SUCCESS,msg);
    }
    public static Result success(Object object){
        return new Result(StateCode.SUCCESS,"操作成功!",object);
    }
    public static Result success(){
        return new Result(StateCode.SUCCESS,"操作成功!");
    }

    public static Result success(String msg,Object object){
        return new Result(StateCode.SUCCESS,msg, object);
    }
}
