package com.cjp.db.exceptions;

import lombok.extern.slf4j.Slf4j;

/**
 * @author chenjianpeng
 */
@Slf4j
public class BaseException extends RuntimeException {
    BaseException(){

    }

    BaseException(String message) {
        super(message);
    }
}
