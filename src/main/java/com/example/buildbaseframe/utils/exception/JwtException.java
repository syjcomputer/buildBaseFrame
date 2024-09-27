package com.example.buildbaseframe.utils.exception;

import lombok.Getter;

/**
 * <b>JWT 异常</b>
 *
 * @author lq
 * @version 1.0
 */
@Getter
public class JwtException extends LogicException {

    /**
     * 存储详细的错误信息
     */
    private String jwtMsg;

    public JwtException setJwtMsg(String jwtMsg) {
        this.jwtMsg = jwtMsg;
        return this;
    }

    public JwtException(String msg) {
        super(msg);
    }

    public JwtException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
