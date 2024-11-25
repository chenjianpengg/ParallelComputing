package com.cjp.db.exceptions;

/**
 * @author chenjianpeng
 */
public class PasswordNotMatchException extends RuntimeException {
    public PasswordNotMatchException(String msg) {
        super(msg);
    }
}
