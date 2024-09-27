package com.example.buildbaseframe.application.user.dto;

import lombok.Data;

/**
 * 密码登录dto
 *
 * @className PwdLoginDto
 * @author LJTTT
 * @date 2022/11/22
 * @version 1.0
 *
 */
@Data
public class PwdLoginDto {
    /**
     * id
     */
    private Long id;

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
    private Long userId;
}
