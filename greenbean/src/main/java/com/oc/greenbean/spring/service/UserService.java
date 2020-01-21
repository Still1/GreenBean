package com.oc.greenbean.spring.service;

import com.oc.greenbean.domain.User;
import com.oc.greenbean.exception.UsernameDuplicatedException;
import com.oc.greenbean.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class UserService {

    private UserMapper userMapper;

    //XXX 解决与DispatcherServletConfig重复
    //XXX 把properties映射成全局静态变量
    @Value("${picturesPath}")
    private String picturesPath;

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

    @CacheEvict(cacheNames = "greenbean", key = "'getUserByUsername'.concat(#username)")
    public void updateNickname(String username, String nickname) {
        userMapper.updateNickname(username, nickname);
    }

    @CacheEvict(cacheNames = "greenbean", key = "'getUserByUsername'.concat(#username)")
    public String updateAvatar(String username, MultipartFile avatar) throws IOException {
        //XXX 把上传图片抽取成公共方法
        String avatarFileName = null;
        if(avatar != null) {
            String avatarFileOriginalFileName = avatar.getOriginalFilename();
            String avatarFileExtension = avatarFileOriginalFileName.substring(avatarFileOriginalFileName.lastIndexOf('.'));
            avatarFileName = UUID.randomUUID().toString() + avatarFileExtension;
            String userHomePath = System.getProperty("user.home").replaceAll("\\\\", "/");
            String picturesPath = userHomePath + this.picturesPath;
            File avatarFolder = new File(picturesPath + "/avatars/");
            if(!avatarFolder.exists()) {
                avatarFolder.mkdir();
            }
            File avatarFile = new File(avatarFolder, avatarFileName);
            //TODO 删除旧头像
            avatar.transferTo(avatarFile.toPath());
        }
        userMapper.updateAvatar(username, avatarFileName);
        return avatarFileName;
    }
}
