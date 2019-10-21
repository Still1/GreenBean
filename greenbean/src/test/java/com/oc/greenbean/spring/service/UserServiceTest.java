package com.oc.greenbean.spring.service;

import com.oc.greenbean.domain.User;
import com.oc.greenbean.mybatis.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;

public class UserServiceTest {

    @Test
    public void testInsertUser() throws NoSuchFieldException, IllegalAccessException {
        UserService userService = new UserService();
        UserMapper mockUserMapper = Mockito.mock(UserMapper.class);
        Class<UserService> userServiceClass = UserService.class;
        Field userMapperField = userServiceClass.getDeclaredField("userMapper");
        userMapperField.setAccessible(true);
        userMapperField.set(userService, mockUserMapper);
        User mockUser = Mockito.mock(User.class);
        userService.insertUser(mockUser);
        Mockito.verify(mockUserMapper).insertUserBasicInfo(mockUser);
        Mockito.verify(mockUserMapper).insertUserAuthority(mockUser.getId(), mockUser.getAuthority());
    }
}
