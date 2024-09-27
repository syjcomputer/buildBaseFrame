package com.example.buildbaseframe.api.common.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <b>认证信息</b>
 * <p>
 *     从请求的Header中能拿到的用户信息
 * </p>
 *
 * @author lq
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authentication {

    /**
     * 用户ID
     */
    private Long id;

}
