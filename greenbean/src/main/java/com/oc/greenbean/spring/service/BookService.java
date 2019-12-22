package com.oc.greenbean.spring.service;

import com.oc.greenbean.mybatis.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookService {
    private BookMapper bookMapper;

    @Autowired
    public BookService(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public List<Map<String, Object>> getSearchBooks(String keyword) {
        List<Map<String, Object>> searchBooks = this.bookMapper.getSearchBooks(keyword);
        return null;
    }
}
