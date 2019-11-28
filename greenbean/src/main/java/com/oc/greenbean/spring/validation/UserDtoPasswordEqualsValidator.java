package com.oc.greenbean.spring.validation;

import com.oc.greenbean.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Spring Validation自定义约束验证器
 * 约束密码与确认密码两个字段必须相等
 *
 * @see UserDtoPasswordEqualsConstraint
 */
public class UserDtoPasswordEqualsValidator implements ConstraintValidator<UserDtoPasswordEqualsConstraint, UserDto> {

    @Override
    public boolean isValid(UserDto value, ConstraintValidatorContext context) {
        String password = value.getPassword();
        String confirmPassword = value.getConfirmPassword();
        return password.equals(confirmPassword);
    }
}
