package com.oc.greenbean.mybatis.mapper;

import com.oc.greenbean.domain.Author;
import com.oc.greenbean.domain.Book;
import com.oc.greenbean.domain.Translator;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BookMapper {
    List<Map<String, Integer>> getSearchBooks(@Param("keyword") String keyword);
    Integer getSearchBooksCount(@Param("keyword") String keyword);
    List<Map<String, Integer>> getSearchBooksWithPagination(@Param("keyword") String keyword, @Param("start") Integer start, @Param("size") Integer size);
    Book getBookBasicInfo(Integer id);
    List<Author> getBookAuthors(Integer id);
    List<Translator> getBookTranslators(Integer id);
    Map<String ,Object> getBookRatingInfo(Integer id);
    void insertBookBasicInfo(Book book);
    void insertBookAuthor(@Param("bookId") Integer bookId, @Param("author") List<Integer> author);
    void insertBookTranslator(@Param("bookId") Integer bookId, @Param("translator") List<Integer> translator);
}
