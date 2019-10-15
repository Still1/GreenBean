package com.oc.greenbean.spring.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserDtoUsernameNotExistsValidator.class)
@Documented
public @interface UserDtoUsernameNotExistsConstraint {
    String message() default "The Username exists.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
