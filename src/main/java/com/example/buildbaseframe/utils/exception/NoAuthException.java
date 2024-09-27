package com.example.buildbaseframe.utils.exception;

/**
 * <b>未登录异常</b>
 * <p>
 *     会返回HTTP 401
 * </p>
 *
 * @author lq
 * @version 1.0
 */
public class NoAuthException extends LogicException {

    public NoAuthException(String message) {
        super(message);
    }

    public NoAuthException() {
        super("用户未登录");
    }

}
