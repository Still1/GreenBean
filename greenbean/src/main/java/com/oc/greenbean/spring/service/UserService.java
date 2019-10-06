package com.oc.greenbean.spring.service;

import com.oc.greenbean.domain.User;
import com.oc.greenbean.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    //FIXME 事务无法生效
    @Transactional
    public void insertUser(User user) {
        userMapper.insertUserBasicInfo(user);
        userMapper.insertUserAuthority(user.getId(), user.getAuthority());
    }
}