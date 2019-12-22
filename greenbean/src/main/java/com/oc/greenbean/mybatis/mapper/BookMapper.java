package com.oc.greenbean.mybatis.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BookMapper {
    List<Map<String, Object>> getSearchBooks(@Param("keyword") String keyword);
}
