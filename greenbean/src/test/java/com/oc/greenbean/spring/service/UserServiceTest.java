package com.oc.greenbean.spring.service;

import com.oc.greenbean.domain.User;
import com.oc.greenbean.mybatis.mapper.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.lang.reflect.Field;

public class UserServiceTest {

    private UserService userService;
    private UserMapper mockUserMapper;

    @BeforeEach
    private void setUp() throws IllegalAccessException, NoSuchFieldException {
        userService = new UserService();
        mockUserMapper = Mockito.mock(UserMapper.class);
        Class<UserService> userServiceClass = UserService.class;
        Field userMapperField = userServiceClass.getDeclaredField("userMapper");
        userMapperField.setAccessible(true);
        userMapperField.set(userService, mockUserMapper);
    }

    @Test
    public void testInsertUser() throws NoSuchFieldException, IllegalAccessException {
        User mockUser = Mockito.mock(User.class);
        userService.insertUser(mockUser);
        InOrder inOrder = Mockito.inOrder(mockUserMapper);
        inOrder.verify(mockUserMapper).insertUserBasicInfo(mockUser);
        inOrder.verify(mockUserMapper).insertUserAuthority(mockUser.getId(), mockUser.getAuthority());
    }

    @Test
    public void testInsertUserWithNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.insertUser(null);
        });
        Mockito.verify(mockUserMapper, Mockito.never()).insertUserBasicInfo(Mockito.any());
        Mockito.verify(mockUserMapper, Mockito.never()).insertUserAuthority(Mockito.any(), Mockito.any());
    }
}
