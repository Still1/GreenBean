package com.oc.greenbean.mybatis.mapper;

import com.oc.greenbean.domain.Author;
import com.oc.greenbean.domain.Book;
import com.oc.greenbean.domain.Translator;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BookMapper {
    List<Map<String, Integer>> getSearchBooks(@Param("keyword") String keyword);
    Book getBookBasicInfo(Integer id);
    List<Author> getBookAuthors(Integer id);
    List<Translator> getBookTranslators(Integer id);
}
