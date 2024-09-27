package com.example.buildbaseframe.application.user.converter;

import com.example.buildbaseframe.application.user.dto.WechatLoginDto;
import com.example.buildbaseframe.infrastructure.user.persistence.repository.po.WechatLoginPo;
import org.mapstruct.Mapper;

/**
 * @InterfaceName: WechatLoginAppConverter
 * @Description:
 * @Author: syj
 * @Date: 2023/4/8
 * @Version: 1.0
 */
@Mapper(componentModel = "spring")
public interface WechatLoginAppConverter {

    WechatLoginDto toWechatLoginDto(WechatLoginPo wechatLoginPo);

    WechatLoginPo toWechatLoginPo(WechatLoginDto wechatLoginDto);
}
