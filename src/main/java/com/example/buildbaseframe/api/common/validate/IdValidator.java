package com.example.buildbaseframe.api.common.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * <b>Id格式验证器</b>
 *
 * @author lq
 * @version 1.0
 */
public class IdValidator implements ConstraintValidator<IdValidate, String> {

    private boolean nullable;

    @Override
    public void initialize(IdValidate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        nullable = constraintAnnotation.nullable();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (nullable && s == null) {
            return true;
        }
        try {
            Long.valueOf(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
