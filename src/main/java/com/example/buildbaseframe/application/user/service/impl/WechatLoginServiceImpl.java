package com.example.buildbaseframe.application.user.service.impl;

import com.example.buildbaseframe.application.common.exception.BusinessException;
import com.example.buildbaseframe.application.common.exception.ExceptionType;
import com.example.buildbaseframe.application.user.converter.WechatLoginAppConverter;
import com.example.buildbaseframe.application.user.dto.WechatLoginDto;
import com.example.buildbaseframe.application.user.service.WechatLoginService;
import com.example.buildbaseframe.infrastructure.user.persistence.repository.WechatLoginRepository;
import com.example.buildbaseframe.infrastructure.user.persistence.repository.po.WechatLoginPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @description:
 * @author: sunyujie
 * @time: 2023/4/8 18:38
 * @version: 1.0
 */
@Service
public class WechatLoginServiceImpl implements WechatLoginService {

    @Autowired
    WechatLoginRepository wechatLoginRepository;

    @Autowired
    WechatLoginAppConverter wechatLoginAppConverter;

    @Override
    public WechatLoginDto findByOpenId(String openId){

        WechatLoginPo wechatLoginPo = wechatLoginRepository.findByOpenId(openId);

        return wechatLoginAppConverter.toWechatLoginDto(wechatLoginPo);
    }

    @Override
    public WechatLoginDto insertOneWechatLogin(WechatLoginDto wechatLoginDto){

        String openId = wechatLoginDto.getOpenId();
        WechatLoginPo wechatLoginPo = wechatLoginAppConverter.toWechatLoginPo(wechatLoginDto);

        if(wechatLoginRepository.findByOpenId(openId)==null){

            wechatLoginPo.setCreateTime(LocalDateTime.now());
            wechatLoginPo.setUpdateTime(LocalDateTime.now());

            wechatLoginPo = wechatLoginRepository.insertOneWechatLogin(wechatLoginPo);
            wechatLoginDto.setId(wechatLoginPo.getId());

        }else {
            throw new BusinessException(ExceptionType.DUPLICATE_ERROR);
        }

        return wechatLoginDto;
    }
}
