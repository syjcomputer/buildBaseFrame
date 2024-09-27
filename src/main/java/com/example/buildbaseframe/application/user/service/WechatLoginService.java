package com.example.buildbaseframe.application.user.service;

import com.example.buildbaseframe.application.user.dto.WechatLoginDto;

/**
 * @InterfaceName: WechatLoginService
 * @Description:
 * @Author: syj
 * @Date: 2023/4/8
 * @Version: 1.0
 */
public interface WechatLoginService {

    public WechatLoginDto findByOpenId(String openId);

    public WechatLoginDto insertOneWechatLogin(WechatLoginDto wechatLoginDto);

}
