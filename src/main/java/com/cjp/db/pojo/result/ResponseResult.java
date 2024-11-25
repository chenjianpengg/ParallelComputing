package com.cjp.db.pojo.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author chenjianpeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult<T> implements Serializable {

    private static final int SUCCESS_CODE = 200;
    private static final String SUCCESS_MEASURE = "success";

    private int code;

    private String message;

    private T data;

    public static <E> ResponseResult<E> success(){
        return new ResponseResult<>(SUCCESS_CODE, SUCCESS_MEASURE, null);
    }

    public static <E> ResponseResult<E> success(E object){
        return new ResponseResult<>(SUCCESS_CODE, SUCCESS_MEASURE, object);
    }

    public static <E> ResponseResult<E> error(int code, String message){
        return new ResponseResult<>(code, message, null);
    }
}
