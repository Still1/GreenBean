package com.oc.greenbean.spring.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserDtoPasswordEqualsValidator.class)
@Documented
public @interface UserDtoPasswordEqualsConstraint {
    String message() default "The password does not equal to the confirm password.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
