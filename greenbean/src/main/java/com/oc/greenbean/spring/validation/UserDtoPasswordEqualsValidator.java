package com.oc.greenbean.spring.validation;

import com.oc.greenbean.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserDtoPasswordEqualsValidator implements ConstraintValidator<UserDtoPasswordEqualsConstraint, UserDto> {

    @Override
    public boolean isValid(UserDto value, ConstraintValidatorContext context) {
        String password = value.getPassword();
        String confirmPassword = value.getConfirmPassword();
        return password.equals(confirmPassword);
    }
}
