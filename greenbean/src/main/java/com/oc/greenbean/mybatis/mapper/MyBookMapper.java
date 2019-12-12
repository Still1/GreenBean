package com.oc.greenbean.mybatis.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyBookMapper {
    Integer getMyBookCount(@Param("type")Short type, @Param("userId")Integer userId);

    List<String> getMyBookPictures(@Param("type")Short type, @Param("userId")Integer userId);
}
