package com.example.buildbaseframe.utils.exception;

/**
 * <b>业务逻辑异常</b>
 * <p>
 *     代替 RuntimeException
 * </p>
 *
 * @author lq
 * @version 1.0
 */
public class LogicException extends RuntimeException {

    public LogicException() {
    }

    public LogicException(String msg) {
        super(msg);
    }

    public LogicException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
