package com.example.buildbaseframe.api.user.req;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 创建微信登陆req
 *
 * @className WechatLogin
 * @author LJTTT
 * @date 2022/11/25
 * @version
 *
 */
@Data
public class WechatLogin {
    /**
     * open id
     */
    @Length(max = 255, message = "openId长度不能超过255")
    private String openId;

    /**
     * 用户id
     */
    private String userId;
}
