package com.example.buildbaseframe.api.user.vo;

import lombok.Data;

/**
 * 微信登录vo
 *
 * @className WechatLoginVo
 * @author LJTTT
 * @date 2022/11/22
 * @version 1.0
 *
 */
@Data
public class WechatLoginVo {
    /**
     * id
     */
    private String id;

    /**
     * open id
     */
    private String openId;

    /**
     * 用户id
     */
    private String userId;
}
