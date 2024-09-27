package com.example.buildbaseframe.application.user.dto;

import lombok.Data;

/**
 * 微信登录dto
 *
 * @className WechatLoginDto
 * @author LJTTT
 * @date 2022/11/22
 * @version 1.0
 *
 */
@Data
public class WechatLoginDto {
    /**
     * id
     */
    private Long id;

    /**
     * open id
     */
    private String openId;

    /**
     * 用户id
     */
    private Long userId;
}
