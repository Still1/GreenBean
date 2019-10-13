package com.oc.greenbean.mybatis.mapper;

import com.oc.greenbean.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;


public interface UserMapper {

    //XXX 把SQL语句写回到XML配置文件中

    @Select("select * from t_user where id = #{id}")
    public User getUserById(Integer id);

    @Select("select * from t_user where username = #{username}")
    public User getUserByUsername(String username);

    @Insert({"insert into t_user (username, password, enabled) ",
        "values(#{username}, #{password}, #{enabled})"})
    @SelectKey(statement="select last_insert_id()", keyProperty="id", before=false, resultType=Integer.class)
    public void insertUserBasicInfo(User user);

    @Insert({"<script>",
        "insert into t_authority (user_id, authority) values ",
        "<foreach item='item' collection='authority' separator=','>",
        "(#{userId}, #{item})",
        "</foreach>",
        "</script>"})
    public void insertUserAuthority(@Param("userId") Integer userId, @Param("authority") List<String> authority);
}
