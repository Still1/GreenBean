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

    /**
     * 插入用户相关的数据
     *
     * @param user 用户数据
     * @throws UsernameDuplicatedException 如果插入的用户数据的用户名已存在，则抛出此异常
     */
    @Transactional
    public void insertUser(User user) throws UsernameDuplicatedException {
        if(this.validateUsernameDuplicated(user.getUsername())) {
            userMapper.insertUserBasicInfo(user);
            userMapper.insertUserAuthority(user.getId(), user.getAuthority());
        } else {
            throw new UsernameDuplicatedException("Username : [" + user.getUsername() + "] has existed in database.");
        }
    }


    /**
     * 验证用户名是否重复
     *
     * @param username 用户名
     * @return 是否重复的结果
     */
    @Cacheable(cacheNames = "greenbean", key = "'validateUsernameDuplicated'.concat(#username)")
    public boolean validateUsernameDuplicated(String username) {
        User user = userMapper.getUserByUsername(username);
        return user == null;
    }


    @Cacheable(cacheNames = "greenbean", key = "'getUserByUsername'.concat(#username)")
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }
}
