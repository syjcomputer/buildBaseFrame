package com.example.buildbaseframe.application.user.dto;

import com.example.buildbaseframe.application.user.enums.GenderEnum;
import lombok.Data;

/**
 * <b>用户信息DTO</b>
 *
 * @author lq
 * @version 1.0
 */
@Data
public class UserInfoDto {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户头像url
     */
    private String avatarUrl;

    /**
     * 用户个人介绍
     */
    private String introduction;

    /**
     * 用户性别
     */
    private GenderEnum gender;

}
