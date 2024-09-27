package com.example.buildbaseframe.application.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <b>自定义业务异常的异常种类</b>
 *
 * @author lq
 * @version 1.0
 */
@Getter
@AllArgsConstructor
public enum ExceptionType {

    /**
     * 参数校验失败
     */
    PARAM_VALIDATE_FAILED(10001, "参数校验失败"),

    /**
     * 用户信息已存在
     */
    DUPLICATE_ERROR(20002, "重复注册错误"),

    /**
     * 操作类型错误
     */
    OPERATION_TYPE_ERROR(30001, "历史记录操作类型出错");

    /**
     * 异常状态码
     */
    private final int code;

    /**
     * 异常信息
     */
    private final String message;

}
