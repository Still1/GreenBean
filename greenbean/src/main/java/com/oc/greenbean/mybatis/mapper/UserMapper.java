package com.oc.greenbean.mybatis.mapper;

import com.oc.greenbean.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserMapper {

    @SuppressWarnings("unused")
    User getUserById(Integer id);

    User getUserByUsername(String username);

    void insertUserBasicInfo(User user);

    void insertUserAuthority(@Param("userId")Integer userId, @Param("authority")List<String> authority);

    void updateNickname(@Param("username")String username, @Param("nickname")String nickname);
}
