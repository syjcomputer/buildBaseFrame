package com.example.buildbaseframe.api.user.converter;


import com.example.buildbaseframe.application.user.enums.GenderEnum;
import com.example.buildbaseframe.utils.converter.EnumConverterHelper;

public interface GenderEnumConverter {
    default GenderEnum toGenderEnum(String value) {
        return EnumConverterHelper.stringToEnum(value, GenderEnum.class);
    }

    default String genderEnumToString(GenderEnum e) {
        return e != null ? e.name() : null;
    }
}
