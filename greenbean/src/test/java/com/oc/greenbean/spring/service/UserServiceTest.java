package com.oc.greenbean.spring.service;

import com.oc.greenbean.domain.User;
import com.oc.greenbean.mybatis.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

class UserServiceTest {

    private UserService userService;
    private UserMapper mockUserMapper;

    @BeforeEach
    void setUp() {
        mockUserMapper = Mockito.mock(UserMapper.class);
        userService = new UserService(mockUserMapper);
    }

    @Test
    void testInsertUser() {
        User mockUser = Mockito.mock(User.class);
        userService.insertUser(mockUser);
        InOrder inOrder = Mockito.inOrder(mockUserMapper);
        inOrder.verify(mockUserMapper).insertUserBasicInfo(mockUser);
        inOrder.verify(mockUserMapper).insertUserAuthority(mockUser.getId(), mockUser.getAuthority());
    }
}
