package com.example.buildbaseframe.api.user.vo;

import lombok.Data;

/**
 * 密码登录vo
 *
 * @className PwdLoginVo
 * @author LJTTT
 * @date 2022/11/22
 * @version 1.0
 *
 */
@Data
public class PwdLoginVo {
    /**
     * id
     */
    private String id;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户id
     */
    private String userId;
}
