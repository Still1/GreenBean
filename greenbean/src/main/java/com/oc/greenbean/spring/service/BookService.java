package com.oc.greenbean.spring.service;

import com.oc.greenbean.domain.Author;
import com.oc.greenbean.domain.Book;
import com.oc.greenbean.domain.Translator;
import com.oc.greenbean.dto.SearchBookItemDto;
import com.oc.greenbean.mybatis.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BookService {
    private BookMapper bookMapper;

    @Autowired
    public BookService(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public List<SearchBookItemDto> getSearchBooks(String keyword) {
        List<Map<String, Integer>> searchBooksId = this.getSearchBooksId(keyword);
        List<SearchBookItemDto> searchBookItemDtos = new ArrayList<>();
        for(Map<String, Integer> idMap : searchBooksId) {
            Integer id = idMap.get("id");
            //TODO 书信息
            SearchBookItemDto dto = new SearchBookItemDto();
            Book book = this.getBookBasicInfo(id);
            this.setBookBasicInfoToDto(dto, book);
            List<Author> authors = this.getBookAuthors(id);
            this.setAuthorsToDto(dto, authors);
            List<Translator> translators = this.getBookTranslators(id);
            this.setTranslatorsToDto(dto,translators);
            searchBookItemDtos.add(dto);
        }
        return searchBookItemDtos;
    }

    public List<Map<String, Integer>> getSearchBooksId(String keyword) {
        return this.bookMapper.getSearchBooks(keyword);
    }

    public Book getBookBasicInfo(Integer id) {
        return this.bookMapper.getBookBasicInfo(id);
    }

    public List<Author> getBookAuthors(Integer id) {
        return this.bookMapper.getBookAuthors(id);
    }

    public List<Translator> getBookTranslators(Integer id) {
        return this.bookMapper.getBookTranslators(id);
    }

    private void setBookBasicInfoToDto(SearchBookItemDto dto, Book book) {
        dto.setBookName(book.getName());
        //XXX 判断是否为空
        dto.setPrice(String.valueOf(book.getPrice()));
        dto.setPublisher(book.getPublisher());
        dto.setPicture(book.getPicture());
        Integer publicationYear = book.getPublicationYear();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(publicationYear);
        Integer publicationMonth = book.getPublicationMonth();
        if(publicationMonth != null) {
            stringBuilder.append("-");
            stringBuilder.append(publicationMonth);
        }
        Integer publicationDay = book.getPublicationDay();
        if(publicationDay != null) {
            stringBuilder.append("-");
            stringBuilder.append(publicationDay);
        }
        dto.setPublicationDate(stringBuilder.toString());
    }

    //XXX 抽取重复
    private void setAuthorsToDto(SearchBookItemDto dto, List<Author> authors) {
        StringBuilder stringBuilder = new StringBuilder();
        for(Author author : authors) {
            stringBuilder.append(author.getName());
            stringBuilder.append(" / ");
        }
        stringBuilder.delete(stringBuilder.length() - 3, stringBuilder.length());
        dto.setAuthorName(stringBuilder.toString());
    }

    private void setTranslatorsToDto(SearchBookItemDto dto, List<Translator> translators) {
        StringBuilder stringBuilder = new StringBuilder();
        for(Translator translator : translators) {
            stringBuilder.append(translator.getName());
            stringBuilder.append(" / ");
        }
        stringBuilder.delete(stringBuilder.length() - 3, stringBuilder.length());
        dto.setTranslatorName(stringBuilder.toString());
    }
}
