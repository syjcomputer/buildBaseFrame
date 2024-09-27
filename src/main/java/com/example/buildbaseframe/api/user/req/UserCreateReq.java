package com.example.buildbaseframe.api.user.req;

import com.example.buildbaseframe.api.common.validate.EnumStringValidate;
import com.example.buildbaseframe.application.user.enums.GenderEnum;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

/**
 * <b>创建用户Req</b>
 *
 * @author lq
 * @version 1.0
 */
@Data
public class UserCreateReq {

    /**
     * 用户昵称
     */
    @Length(min = 2, max = 64, message = "昵称长度应在2-64之间")
    private String nickname;

    /**
     * 用户头像url
     */
    @URL(message = "头像应当是有效的图片url")
    @Length(max = 255, message = "头像URL长度不能超过255")
    private String avatarUrl;

    /**
     * 用户个人介绍
     */
    @Length(max = 255, message = "用户介绍长度不能超过255")
    private String introduction;

    /**
     * 用户性别
     */
    @EnumStringValidate(value = GenderEnum.class, message = "不是有效的性别类型")
    private String gender;

}
