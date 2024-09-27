package com.example.buildbaseframe.api.common.respond;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <b>通用返回结果</b>
 *
 * @author lq
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult {

    private static final Integer SUCCESS_CODE = 200;
    private static final String SUCCESS_MESSAGE = "操作成功";

    /**
     * 是否成功（必填）
     */
    private Boolean success;
    /**
     * 业务状态码（必填）
     */
    private Integer code;
    /**
     * 对业务状态码的解释（必填）
     */
    private String message;
    /**
     * 返回数据（选填）
     */
    private Object data;

    public static CommonResult success() {
        return new CommonResult(true, SUCCESS_CODE, SUCCESS_MESSAGE, new Object());
    }

    public static CommonResult success(Object data) {
        return new CommonResult(true, SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    public static CommonResult failure(String message) {
        return new CommonResult(false, -1, message, new Object());
    }

    public static CommonResult failure(Integer code, String message) {
        return new CommonResult(false, code, message, new Object());
    }

}