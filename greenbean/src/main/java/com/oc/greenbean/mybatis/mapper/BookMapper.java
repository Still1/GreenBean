package com.oc.greenbean.mybatis.mapper;

import com.oc.greenbean.domain.Author;
import com.oc.greenbean.domain.Book;
import com.oc.greenbean.domain.Translator;
import com.oc.greenbean.dto.UserRatingDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BookMapper {
    Integer getSearchBooksCount(@Param("keyword") String keyword);
    List<Map<String, Integer>> getSearchBooksWithPagination(@Param("keyword") String keyword, @Param("start") Integer start, @Param("size") Integer size);
    Book getBookBasicInfo(Integer id);
    Map<String ,Object> getBookRatingInfo(Integer id);
    List<Map<String ,Object>> getBookRatingCountGroupByScore(Integer id);
    void insertBookBasicInfo(Book book);
    void insertBookAuthor(@Param("bookId") Integer bookId, @Param("author") List<Integer> author);
    void insertBookTranslator(@Param("bookId") Integer bookId, @Param("translator") List<Integer> translator);
    List<String> getAuthorSuggestion(String keyword);
    List<String> getTranslatorSuggestion(String keyword);
    List<Integer> getAuthorIdByName(@Param("authorName") String authorName);
    List<Integer> getTranslatorIdByName(@Param("translatorName") String translatorName);
    void insertAuthor(Author author);
    void insertTranslator(Translator translator);
    void insertUserRating(UserRatingDto dto);
    int getUserRatingCount(@Param("bookId") Integer bookId, @Param("userId") Integer userId);
    Map<String, Object> getBookUserRatingInfo(@Param("bookId") Integer bookId, @Param("userId") Integer userId);
    void updateUserRating(UserRatingDto dto);
    void removeUserRating(@Param("bookId") Integer bookId, @Param("userId") Integer userId);
    Integer getBookCommentCount(Integer bookId);
    List<Map<String, Object>> getUserComment(@Param("bookId") Integer bookId, @Param("start") Integer start, @Param("size") Integer size);
    Integer getBookReadCount(@Param("bookId") Integer bookId, @Param("type") Integer type);
    List<Map<String, Object>> getUserRead(@Param("bookId") Integer bookId, @Param("start") Integer start, @Param("size") Integer size);
    void updateBookBasicInfo(Book book);
    void removeBookAuthor(Integer bookId);
    void removeBookTranslator(Integer bookId);
}
