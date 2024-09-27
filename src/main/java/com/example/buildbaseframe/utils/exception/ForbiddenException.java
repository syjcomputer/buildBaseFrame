package com.example.buildbaseframe.utils.exception;

/**
 * <b>无权访问/非法操作异常</b>
 * <p>
 *     会返回HTTP 403
 * </p>
 *
 * @author lq
 * @version 1.0
 */
public class ForbiddenException extends LogicException {

    public ForbiddenException(String message) {
        super(message);
    }

}
