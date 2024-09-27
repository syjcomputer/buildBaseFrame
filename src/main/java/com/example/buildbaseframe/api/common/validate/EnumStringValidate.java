package com.example.buildbaseframe.api.common.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * <b>枚举字符串格式验证</b>
 * <p>
 *     若传入非枚举类型则该注解不生效
 * </p>
 *
 * @author lq
 * @version 1.0
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {EnumStringValidator.class})
public @interface EnumStringValidate {

    Class<? extends Enum<?>> value();

    String message() default "";

    boolean nullable() default false;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
