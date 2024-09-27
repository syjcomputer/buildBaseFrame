package com.example.buildbaseframe.api.user.vo;

import lombok.Data;

/**
 * @description:
 * @author: sunyujie
 * @time: 2023/4/7 22:46
 * @version: 1.0
 */
@Data
public class RegisterVo {
    /**
     * 用户id
     */
    private String id;

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
    private String gender;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;
}
