package com.example.buildbaseframe.application.user.converter;

import com.example.buildbaseframe.application.user.dto.UserInfoDto;
import com.example.buildbaseframe.application.user.enums.GenderEnum;
import com.example.buildbaseframe.infrastructure.user.persistence.repository.po.UserPo;
import com.example.buildbaseframe.utils.converter.EnumConverterHelper;
import org.mapstruct.Mapper;

/**
 * <b>用户服务层数据转换器</b>
 *
 * @author lq
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface UserAppConverter {

    default GenderEnum toGenderEnum(Integer value) {
        return EnumConverterHelper.integerToEnum(value, GenderEnum.class);
    }

    default Integer genderEnumToInteger(GenderEnum e) {
        return e != null ? e.ordinal() : null;
    }

    UserInfoDto toUserInfoDto(UserPo po);

    UserPo toUserPo(UserInfoDto dto);

}
