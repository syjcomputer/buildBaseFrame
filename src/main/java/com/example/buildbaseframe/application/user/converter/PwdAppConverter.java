package com.example.buildbaseframe.application.user.converter;

import com.example.buildbaseframe.application.user.dto.PwdLoginDto;
import com.example.buildbaseframe.infrastructure.user.persistence.repository.po.PwdLoginPo;
import org.mapstruct.Mapper;

/**
 * @InterfaceName: PwdAppConverter
 * @Description:
 * @Author: syj
 * @Date: 2023/4/6
 * @Version: 1.0
 */
@Mapper(componentModel = "spring")
public interface PwdAppConverter {

    PwdLoginDto toPwdLoginDto(PwdLoginPo pwdLoginPo);

    PwdLoginPo toPwdLoginPo(PwdLoginDto pwdLoginDto);
}
