package com.example.buildbaseframe.api.user.vo;

import lombok.Data;

/**
 * <b>用户信息Vo</b>
 *
 * @author lq
 * @version 1.0
 */
@Data
public class UserInfoVo {

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

}
