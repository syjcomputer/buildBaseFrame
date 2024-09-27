package com.example.buildbaseframe.utils.exception;

/**
 * <b>资源不存在异常</b>
 * <p>
 *     会返回HTTP 404
 * </p>
 *
 * @author lq
 * @version 1.0
 */
public class NotFoundException extends LogicException {

    public NotFoundException(String message) {
        super(message);
    }

}
