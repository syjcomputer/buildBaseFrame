package com.example.buildbaseframe.api.user.converter;

import com.example.buildbaseframe.api.user.vo.PwdLoginVo;
import com.example.buildbaseframe.application.user.dto.PwdLoginDto;
import org.mapstruct.Mapper;

/**
 * @InterfaceName: PwdLoginApiConverter
 * @Description:
 * @Author: syj
 * @Date: 2023/4/6
 * @Version: 1.0
 */
@Mapper(componentModel = "spring")
public interface PwdLoginApiConverter {

    PwdLoginDto toPwdLoginDto(PwdLoginVo pwdLoginVo);

    PwdLoginVo toPwdLoginVo(PwdLoginDto pwdLoginDto);
}
