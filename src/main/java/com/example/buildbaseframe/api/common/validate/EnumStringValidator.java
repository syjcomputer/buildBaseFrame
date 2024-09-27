package com.example.buildbaseframe.api.common.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * <b>枚举字符串格式验证器</b>
 *
 * @author lq
 * @version 1.0
 */
// ConstraintValidator<EnumStringValidate, String>表示输入EnumStringValidate，输出string
public class EnumStringValidator implements ConstraintValidator<EnumStringValidate, String> {

    private boolean nullable;
    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(EnumStringValidate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        nullable = constraintAnnotation.nullable();
        enumClass = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (!enumClass.isEnum()) {
            return true;
        }
        if (nullable && s == null) {
            return true;
        }
        if (s == null) {
            return false;
        }
        for (var e : enumClass.getEnumConstants()) {
            if (e.name().equals(s)) {
                return true;
            }
        }
        return false;
    }

}
