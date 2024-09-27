package com.example.buildbaseframe.application.common.exception;

import com.example.buildbaseframe.utils.exception.LogicException;
import lombok.Getter;

/**
 * <b>业务异常</b>
 * <p>
 *     会返回HTTP 200,但是会在response的body部分标注请求失败及失败原因
 * </p>
 *
 * @author lq
 * @version 1.0
 */
@Getter
public class BusinessException extends LogicException {

    private final int code;

    public BusinessException(ExceptionType type) {
        super(type.getMessage());
        this.code = type.getCode();
    }

    public BusinessException(ExceptionType type, String message) {
        super(type.getMessage() + ": " + message);
        this.code = type.getCode();
    }

}
