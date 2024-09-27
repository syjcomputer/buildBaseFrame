package com.example.buildbaseframe.application.user.service;

import com.example.buildbaseframe.application.user.dto.PwdLoginDto;
import com.example.buildbaseframe.application.user.dto.UserInfoDto;
import org.apache.ibatis.javassist.NotFoundException;

/**
 * @InterfaceName: PwdLoginService
 * @Description:
 * @Author: syj
 * @Date: 2023/4/6
 * @Version: 1.0
 */
public interface PwdLoginService {
    public boolean verifyPwdLogin(UserInfoDto user, String pwd);

    public void insertOneLoginPwd(PwdLoginDto pwdLoginDto);
}
