package com.oc.greenbean.spring.service;

import com.oc.greenbean.domain.User;
import com.oc.greenbean.exception.UsernameDuplicatedException;
import com.oc.greenbean.mybatis.mapper.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

class UserServiceTest {

    private static String existUsername = "exist";
    private static String notExistUsername = "notExist";

    private UserService userService;
    private UserMapper mockUserMapper;

    @BeforeEach
    void setUp() {
        mockUserMapper = Mockito.mock(UserMapper.class);
        userService = new UserService(mockUserMapper);
    }

    @Test
    void testInsertUser() {
        Mockito.when(mockUserMapper.getUserByUsername(notExistUsername)).thenReturn(null);
        Mockito.doAnswer((invocationOnMock) -> {
            User mockUser = invocationOnMock.getArgument(0);
            Mockito.when(mockUser.getId()).thenReturn(555);
            return null;
        }).when(mockUserMapper).insertUserBasicInfo(Mockito.any(User.class));
        User mockUser = Mockito.mock(User.class);
        Mockito.when(mockUser.getUsername()).thenReturn(notExistUsername);
        userService.insertUser(mockUser);
        InOrder inOrder = Mockito.inOrder(mockUserMapper);
        inOrder.verify(mockUserMapper).insertUserBasicInfo(mockUser);
        inOrder.verify(mockUserMapper).insertUserAuthority(555, mockUser.getAuthority());
    }

    @Test
    void testInsertUserWithDuplicatedUsername() {
        Mockito.when(mockUserMapper.getUserByUsername(existUsername)).thenReturn(Mockito.mock(User.class));
        User mockUser = Mockito.mock(User.class);
        Mockito.when(mockUser.getUsername()).thenReturn(existUsername);
        Assertions.assertThrows(UsernameDuplicatedException.class, () -> {
            userService.insertUser(mockUser);
        });
    }

    @Test
    void testValidateUsernameDuplicatedExist() {
        Mockito.when(mockUserMapper.getUserByUsername(existUsername)).thenReturn(Mockito.mock(User.class));
        boolean validateUserA = userService.validateUsernameDuplicated(existUsername);
        Assertions.assertEquals(false, validateUserA);
    }

    @Test
    void testValidateUsernameDuplicatedNotExist() {
        Mockito.when(mockUserMapper.getUserByUsername(notExistUsername)).thenReturn(null);
        boolean validateUserB = userService.validateUsernameDuplicated(notExistUsername);
        Assertions.assertEquals(true, validateUserB);
    }
}
