package com.example.buildbaseframe.api.user.req;

import com.example.buildbaseframe.api.common.validate.IdValidate;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

/**
 * 创建密码登录req
 *
 * @className PwdLoginCreateReq
 * @author LJTTT
 * @date 2022/11/25
 * @version
 *
 */
@Data
public class PwdLoginCreateReq {
    /**
     * 邮箱
     */
    @Email(message = "email应当是有效的email地址")
    @Length(max = 255, message = "email长度不能超过255")
    private String email;

    /**
     * 密码
     */
    @Length(max = 255, message = "密码长度不能超过255")
    private String password;

    /**
     * 用户id
     */
    @IdValidate(message = "不是有效的id")
    private String userId;
}
