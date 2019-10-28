package com.oc.greenbean.spring.validation;

import com.oc.greenbean.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserDtoPasswordEqualsValidatorTest {

    private UserDtoPasswordEqualsValidator validator;

    @BeforeEach
    void setup() {
         validator = new UserDtoPasswordEqualsValidator();
    }

    @Test
    void testIsValidPasswordEquals() {
        UserDto userDto = new UserDto();
        userDto.setPassword("a");
        userDto.setConfirmPassword("a");
        Assertions.assertTrue(validator.isValid(userDto, null));
    }

    @Test
    void testIsValidPasswordNotEquals() {
        UserDto userDto = new UserDto();
        userDto.setPassword("a");
        userDto.setConfirmPassword("b");
        Assertions.assertFalse(validator.isValid(userDto, null));
    }
}
