package com.oc.greenbean.mybatis.mapper;

import com.oc.greenbean.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserMapper {

    //XXX 把SQL语句写回到XML配置文件中

    @SuppressWarnings("unused")
    User getUserById(Integer id);

    User getUserByUsername(String username);

    void insertUserBasicInfo(User user);

    void insertUserAuthority(@Param("userId") Integer userId, @Param("authority") List<String> authority);
}
