package com.oc.greenbean.spring.service;

import com.oc.greenbean.domain.Author;
import com.oc.greenbean.domain.Book;
import com.oc.greenbean.domain.Translator;
import com.oc.greenbean.dto.SearchBookItemDto;
import com.oc.greenbean.dto.SearchPageDto;
import com.oc.greenbean.mybatis.mapper.BookMapper;
import com.oc.greenbean.vo.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BookService {
    private BookMapper bookMapper;

    @Value("${greenbean.pagination.size}")
    private Integer paginationSize;

    @Autowired
    public BookService(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public SearchPageDto getSearchPage(String keyword, Integer start) {
        SearchPageDto searchPageDto = new SearchPageDto();
        List<SearchBookItemDto> bookItems = this.getSearchBooksInOnePage(keyword, start);
        searchPageDto.setBookItems(bookItems);
        Integer totalBookItemsCount = this.getSearchBooksCount(keyword);
        Pagination pagination = new Pagination(paginationSize, start, totalBookItemsCount);
        searchPageDto.setPagination(pagination);
        return searchPageDto;
    }

    private Integer getSearchBooksCount(String keyword) {
        return this.bookMapper.getSearchBooksCount(keyword);
    }

    private List<SearchBookItemDto> getSearchBooksInOnePage(String keyword, Integer start) {
        List<Map<String, Integer>> searchBooksId = this.getSearchBooksIdInOnePage(keyword, start);
        List<SearchBookItemDto> searchBookItemDtos = new ArrayList<>();
        for(Map<String, Integer> idMap : searchBooksId) {
            Integer id = idMap.get("id");
            SearchBookItemDto dto = new SearchBookItemDto();
            Book book = this.getBookBasicInfo(id);
            this.setBookBasicInfoToDto(dto, book);
            List<Author> authors = this.getBookAuthors(id);
            this.setAuthorsToDto(dto, authors);
            List<Translator> translators = this.getBookTranslators(id);
            this.setTranslatorsToDto(dto,translators);
            Map<String, Object> ratingInfo = this.getBookRatingInfo(id);
            this.setRatingInfoToDto(dto, ratingInfo);
            searchBookItemDtos.add(dto);
        }
        return searchBookItemDtos;
    }

    private List<Map<String, Integer>> getSearchBooksIdInOnePage(String keyword, Integer start) {
        return this.bookMapper.getSearchBooksWithPagination(keyword, start, paginationSize);
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

    public Map<String, Object> getBookRatingInfo(Integer id) {
        return this.bookMapper.getBookRatingInfo(id);
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
        if(translators.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for(Translator translator : translators) {
                stringBuilder.append(translator.getName());
                stringBuilder.append(" / ");
            }
            if(stringBuilder.length() > 0) {
                stringBuilder.delete(stringBuilder.length() - 3, stringBuilder.length());
            }
            dto.setTranslatorName(stringBuilder.toString());
        }
    }

    private void setRatingInfoToDto(SearchBookItemDto dto, Map<String, Object> ratingInfo) {
        Long ratingCount = (Long)ratingInfo.get("ratingCount");
        dto.setRatingCount(ratingCount.intValue());
        if(ratingCount > 0) {
            BigDecimal rating = (BigDecimal)ratingInfo.get("rating");
            BigDecimal ratingWithOneDecimal = rating.setScale(1, RoundingMode.HALF_UP);
            DecimalFormat decimalFormat = new DecimalFormat("#.0");
            dto.setRating(decimalFormat.format(ratingWithOneDecimal.floatValue()));

            BigDecimal ratingWithNoDecimal = rating.setScale(0, RoundingMode.HALF_UP);
            BigDecimal starSuffixNumber = ratingWithNoDecimal.divide(new BigDecimal(2)).multiply(new BigDecimal(10));
            decimalFormat = new DecimalFormat("00");
            String starClassName = "star" + decimalFormat.format(starSuffixNumber.longValue());
            dto.setStarClassName(starClassName);
        } else {
            //XXX 修改硬编码
            dto.setStarClassName("star00");
        }
    }
}
