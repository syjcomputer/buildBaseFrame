package com.example.buildbaseframe.application.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.buildbaseframe.application.user.dto.UserInfoDto;

/**
 * <b>用户服务接口</b>
 *
 * @author lq
 * @version 1.0
 */
public interface UserService {

    public UserInfoDto getUserInfo(Long userId);

    public UserInfoDto updateUserInfo(UserInfoDto dto, Long userId);

    public UserInfoDto getUserByname(String name);

    public Long insertOneUser(UserInfoDto userInfoDto);
}
