package com.cjp.db.exceptions;

/**
 * @author chenjianpeng
 */
public class UserNotExistException extends BaseException{
    public UserNotExistException(String msg) {
        super(msg);
    }
}
