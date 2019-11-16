package com.oc.greenbean.spring.service;

import com.oc.greenbean.domain.User;
import com.oc.greenbean.exception.UsernameDuplicatedException;
import com.oc.greenbean.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Transactional
    public void insertUser(User user) throws UsernameDuplicatedException {
        if(this.validateUsernameDuplicated(user.getUsername())) {
            userMapper.insertUserBasicInfo(user);
            userMapper.insertUserAuthority(user.getId(), user.getAuthority());
        } else {
            throw new UsernameDuplicatedException("Username : [" + user.getUsername() + "] has existed in database.");
        }
    }


    @Cacheable(cacheNames = "userService" )
    public boolean validateUsernameDuplicated(String username) {
        User user = userMapper.getUserByUsername(username);
        return user == null;
    }
}
