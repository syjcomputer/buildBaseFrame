package com.example.buildbaseframe.api.user.converter;

import com.example.buildbaseframe.api.user.req.UserCreateReq;
import com.example.buildbaseframe.api.user.vo.UserInfoVo;
import com.example.buildbaseframe.application.user.dto.UserInfoDto;
import com.example.buildbaseframe.application.user.enums.GenderEnum;
import com.example.buildbaseframe.utils.converter.EnumConverterHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * <b>用户接口层数据转换器</b>
 *
 * @author lq
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface UserApiConverter extends GenderEnumConverter{

    default GenderEnum toGenderEnum(String value) {
        return EnumConverterHelper.stringToEnum(value, GenderEnum.class);
//        return EnumConverterHelper.integerToEnum(value, GenderEnum.class);
    }

    default String genderEnumToString(GenderEnum e) {
        return e != null ? e.name() : null;
    }


//    @Mapping(source = "introduction", target = "description")
    UserInfoVo toUserInfoVo(UserInfoDto dto);

//    @Mapping(source = "description", target = "introduction")
    UserInfoDto toUserInfoDto(UserCreateReq req);

//    @Named("introduction2description")
//    default Long userDto2userId(UserInfoDto owner){
//        return owner.getId();
//    }

}
