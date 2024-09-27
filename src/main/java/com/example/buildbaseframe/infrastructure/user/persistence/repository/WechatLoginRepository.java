package com.example.buildbaseframe.infrastructure.user.persistence.repository;

import com.example.buildbaseframe.infrastructure.user.persistence.repository.po.WechatLoginPo;

/**
 * 微信登录基础设施层持久化接口
 *
 * @className WechatLoginRepository
 * @author LJTTT
 * @date 2022/11/22
 * @version
 *
 */
public interface WechatLoginRepository {

    public WechatLoginPo findByOpenId(String openId);

    public WechatLoginPo insertOneWechatLogin(WechatLoginPo wechatLoginPo);
}
