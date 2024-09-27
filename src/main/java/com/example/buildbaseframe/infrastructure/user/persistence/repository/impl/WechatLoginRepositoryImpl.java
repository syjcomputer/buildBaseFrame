package com.example.buildbaseframe.infrastructure.user.persistence.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.buildbaseframe.infrastructure.user.persistence.mysql.mapper.WechatLoginPoMapper;
import com.example.buildbaseframe.infrastructure.user.persistence.repository.WechatLoginRepository;
import com.example.buildbaseframe.infrastructure.user.persistence.repository.po.WechatLoginPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: sunyujie
 * @time: 2023/4/8 18:36
 * @version: 1.0
 */
@Repository
public class WechatLoginRepositoryImpl implements WechatLoginRepository {

    @Autowired
    WechatLoginPoMapper wechatLoginPoMapper;

    @Override
    public WechatLoginPo findByOpenId(String openId){
//        QueryWrapper<WechatLoginPo> queryWrapper = new QueryWrapper<>();
//        WechatLoginPo wechatLoginPo = wechatLoginPoMapper.selectOne( queryWrapper.eq("openId", openId));

        WechatLoginPo wechatLoginPo = wechatLoginPoMapper.findByOpenId(openId);

//        List<WechatLoginPo> userList

        return wechatLoginPo;
    }

    @Override
    public WechatLoginPo insertOneWechatLogin(WechatLoginPo wechatLoginPo){

        wechatLoginPoMapper.insert(wechatLoginPo);

        return wechatLoginPo;
    }

}
