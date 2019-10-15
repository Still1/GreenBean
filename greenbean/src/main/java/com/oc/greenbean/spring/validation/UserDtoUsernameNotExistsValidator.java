package com.oc.greenbean.spring.validation;

import com.oc.greenbean.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserDtoUsernameNotExistsValidator implements ConstraintValidator<UserDtoUsernameNotExistsConstraint, String> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return userService.validateUsername(value);
    }
}
