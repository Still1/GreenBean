package com.oc.greenbean.spring.service;

import com.oc.greenbean.domain.User;
import com.oc.greenbean.exception.UsernameDuplicatedException;
import com.oc.greenbean.mybatis.mapper.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.*;

class UserServiceTest {

    private static String existUsername = "exist";
    private static String notExistUsername = "notExist";
    private static String defaultPassword = "password";
    private static Integer existId = 100;
    private static Integer userIdGeneratedByDatabase = 555;

    private UserService userService;
    private UserMapper mockUserMapper;

    @BeforeEach
    void setup() {
        mockUserMapper = Mockito.mock(UserMapper.class);
        userService = new UserService(mockUserMapper);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testInsertUser() {
        Mockito.when(mockUserMapper.getUserByUsername(notExistUsername)).thenReturn(null);
        User user = this.generateUser(false);
        Mockito.doAnswer((invocationOnMock) -> {
            User userArgument = invocationOnMock.getArgument(0);
            userArgument.setId(userIdGeneratedByDatabase);
            return null;
        }).when(mockUserMapper).insertUserBasicInfo(user);
        userService.insertUser(user);
        InOrder inOrder = Mockito.inOrder(mockUserMapper);
        inOrder.verify(mockUserMapper).insertUserBasicInfo(user);
        inOrder.verify(mockUserMapper).insertUserAuthority(userIdGeneratedByDatabase, user.getAuthority());
    }

    @Test
    void testInsertUserWithDuplicatedUsername() {
        User existUser = this.generateUser(true);
        Mockito.when(mockUserMapper.getUserByUsername(existUsername)).thenReturn(existUser);
        User user = new User();
        user.setUsername(existUsername);
        Assertions.assertThrows(UsernameDuplicatedException.class, () -> userService.insertUser(user));
    }

    @Test
    void testValidateUsernameDuplicatedExist() {
        User existUser = this.generateUser(true);
        Mockito.when(mockUserMapper.getUserByUsername(existUsername)).thenReturn(existUser);
        boolean validateUser = userService.validateUsernameDuplicated(existUsername);
        Assertions.assertFalse(validateUser);
    }

    @Test
    void testValidateUsernameDuplicatedNotExist() {
        Mockito.when(mockUserMapper.getUserByUsername(notExistUsername)).thenReturn(null);
        boolean validateUser = userService.validateUsernameDuplicated(notExistUsername);
        Assertions.assertTrue(validateUser);
    }

    private User generateUser(boolean exist) {
        User user = new User();
        if(exist) {
            user.setId(existId);
            user.setUsername(existUsername);
        } else {
            user.setUsername(notExistUsername);
        }
        user.setPassword(defaultPassword);
        user.setEnabled(true);
        List<String> authority = new ArrayList<>();
        authority.add("USER");
        user.setAuthority(authority);
        return user;
    }
}
