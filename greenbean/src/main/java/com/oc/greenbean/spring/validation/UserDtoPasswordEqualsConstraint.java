package com.oc.greenbean.spring.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Spring Validation自定义约束
 * 约束密码与确认密码两个字段必须相等
 *
 * @see UserDtoPasswordEqualsValidator
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserDtoPasswordEqualsValidator.class)
@Documented
public @interface UserDtoPasswordEqualsConstraint {

    @SuppressWarnings("unused")
    String message() default "The password does not equal to the confirm password.";

    Class<?>[] groups() default { };

    @SuppressWarnings("unused")
    Class<? extends Payload>[] payload() default { };
}
