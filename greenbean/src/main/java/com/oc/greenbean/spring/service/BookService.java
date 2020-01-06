package com.oc.greenbean.spring.service;

import com.oc.greenbean.domain.Author;
import com.oc.greenbean.domain.Book;
import com.oc.greenbean.domain.Translator;
import com.oc.greenbean.dto.BookDto;
import com.oc.greenbean.dto.BookItemDto;
import com.oc.greenbean.dto.SearchPageDto;
import com.oc.greenbean.mybatis.mapper.BookMapper;
import com.oc.greenbean.vo.Pagination;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
        List<BookItemDto> bookItems = this.getSearchBooksInOnePage(keyword, start);
        searchPageDto.setBookItems(bookItems);
        Integer totalBookItemsCount = this.getSearchBooksCount(keyword);
        Pagination pagination = new Pagination(paginationSize, start, totalBookItemsCount);
        searchPageDto.setPagination(pagination);
        return searchPageDto;
    }

    public BookItemDto getBookPage(Integer id) {
        return this.getBookItemDtoById(id);
    }

    @Transactional
    public void saveBook(BookDto bookDto) {
        Book book = this.generateBook(bookDto);
        this.bookMapper.insertBookBasicInfo(book);
        //XXX 考虑重名作者与译者
        //XXX 处理作者与处理译者的代码重复
        List<Integer> authorIds = new ArrayList<>();
        for(String authorName : bookDto.getAuthor()) {
            List<Integer> resultList = this.bookMapper.getAuthorIdByName(authorName);
            if(resultList.size() >= 1) {
                authorIds.add(resultList.get(0));
            } else {
                Author author = new Author();
                author.setName(authorName);
                this.bookMapper.insertAuthor(author);
                authorIds.add(author.getId());
            }
        }
        if(authorIds.size() > 0) {
            this.bookMapper.insertBookAuthor(book.getId(), authorIds);
        }

        List<Integer> translatorIds = new ArrayList<>();
        for(String translatorName : bookDto.getTranslator()) {
            List<Integer> resultList = this.bookMapper.getTranslatorIdByName(translatorName);
            if(resultList.size() >= 1) {
                translatorIds.add(resultList.get(0));
            } else {
                Translator translator = new Translator();
                translator.setName(translatorName);
                this.bookMapper.insertTranslator(translator);
                translatorIds.add(translator.getId());
            }
        }
        if(translatorIds.size() > 0) {
            this.bookMapper.insertBookTranslator(book.getId(), translatorIds);

        }
    }

    public List<String> getAuthorSuggestion(String keyword) {
        return this.bookMapper.getAuthorSuggestion(keyword);
    }

    public List<String> getTranslatorSuggestion(String keyword) {
        return this.bookMapper.getTranslatorSuggestion(keyword);
    }

    private Book generateBook(BookDto bookDto) {
        Book book = new Book();
        //XXX 反射处理
        book.setName(bookDto.getName());
        book.setIsbn(bookDto.getIsbn());
        book.setPrice(bookDto.getPrice());
        book.setPublisher(bookDto.getPublisher());
        book.setPublicationYear(bookDto.getPublicationYear());
        book.setSubtitle(bookDto.getSubtitle());
        book.setOriginalName(bookDto.getOriginalName());
        book.setBinding(bookDto.getBinding());
        book.setPage(bookDto.getPage());
        book.setContentIntro(bookDto.getContentIntro());
        book.setAuthorIntro(bookDto.getAuthorIntro());
        book.setDirectory(bookDto.getDirectory());
        if(bookDto.getPublicationMonth() != 0) {
            book.setPublicationMonth(bookDto.getPublicationMonth());
        }
        if(bookDto.getPublicationDay() != 0) {
            book.setPublicationDay(bookDto.getPublicationDay());
        }
        return book;
    }

    private Integer getSearchBooksCount(String keyword) {
        return this.bookMapper.getSearchBooksCount(keyword);
    }

    private List<BookItemDto> getSearchBooksInOnePage(String keyword, Integer start) {
        List<Map<String, Integer>> searchBooksId = this.getSearchBooksIdInOnePage(keyword, start);
        List<BookItemDto> bookItemDtos = new ArrayList<>();
        for(Map<String, Integer> idMap : searchBooksId) {
            Integer id = idMap.get("id");
            bookItemDtos.add(this.getBookItemDtoById(id));
        }
        return bookItemDtos;
    }

    private BookItemDto getBookItemDtoById(Integer id) {
        BookItemDto dto = new BookItemDto();
        Book book = this.getBookBasicInfo(id);
        this.setBookBasicInfoToDto(dto, book);
        List<Author> authors = this.getBookAuthors(id);
        this.setAuthorsToDto(dto, authors);
        List<Translator> translators = this.getBookTranslators(id);
        this.setTranslatorsToDto(dto,translators);
        Map<String, Object> ratingInfo = this.getBookRatingInfo(id);
        List<Map<String, Object>> ratingCountGroupByScore = this.getBookRatingCountGroupByScore(id);
        this.setRatingInfoToDto(dto, ratingInfo);
        this.setRatingPercentageList(dto, ratingCountGroupByScore);
        this.setRatingPowerWidthPercentageList(dto, ratingCountGroupByScore);
        return dto;
    }

    private List<Map<String, Integer>> getSearchBooksIdInOnePage(String keyword, Integer start) {
        return this.bookMapper.getSearchBooksWithPagination(keyword, start, paginationSize);
    }

    private Book getBookBasicInfo(Integer id) {
        return this.bookMapper.getBookBasicInfo(id);
    }

    private List<Author> getBookAuthors(Integer id) {
        return this.bookMapper.getBookAuthors(id);
    }

    private List<Translator> getBookTranslators(Integer id) {
        return this.bookMapper.getBookTranslators(id);
    }

    private Map<String, Object> getBookRatingInfo(Integer id) {
        return this.bookMapper.getBookRatingInfo(id);
    }

    private List<Map<String, Object>> getBookRatingCountGroupByScore(Integer id) {
        return this.bookMapper.getBookRatingCountGroupByScore(id);
    }

    private void setBookBasicInfoToDto(BookItemDto dto, Book book) {
        dto.setId(String.valueOf(book.getId()));
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
        dto.setOriginalName(book.getOriginalName());
        dto.setPage(String.valueOf(book.getPage()));
        dto.setPrice(String.valueOf(book.getPrice()));
        //XXX 硬编码
        String bindingString = null;
        Integer binding = book.getBinding();
        if(binding != null) {
            if(binding == 1) {
                bindingString = "平装";
            } else if(binding == 2) {
                bindingString = "精装";
            }
        }
        dto.setBinding(bindingString);
        dto.setContentIntro(this.separateParagraph(book.getContentIntro()));
        dto.setAuthorIntro(this.separateParagraph(book.getAuthorIntro()));
        dto.setDirectory(this.separateParagraph(book.getDirectory()));
    }

    private List<String> separateParagraph(String string) {
        List<String> stringList = null;
        if(StringUtils.isNotBlank(string)) {
            stringList = Arrays.asList(string.split("\\n+"));
        }
        return stringList;
    }

    //XXX 抽取重复
    private void setAuthorsToDto(BookItemDto dto, List<Author> authors) {
        StringBuilder stringBuilder = new StringBuilder();
        for(Author author : authors) {
            stringBuilder.append(author.getName());
            stringBuilder.append(" / ");
        }
        stringBuilder.delete(stringBuilder.length() - 3, stringBuilder.length());
        dto.setAuthorName(stringBuilder.toString());
    }

    private void setTranslatorsToDto(BookItemDto dto, List<Translator> translators) {
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

    private void setRatingInfoToDto(BookItemDto dto, Map<String, Object> ratingInfo) {
        Long ratingCount = (Long)ratingInfo.get("ratingCount");
        dto.setRatingCount(String.valueOf(ratingCount.intValue()));
        if(ratingCount > 0) {
            BigDecimal rating = (BigDecimal)ratingInfo.get("rating");
            BigDecimal ratingWithOneDecimal = rating.setScale(1, RoundingMode.HALF_UP);
            DecimalFormat decimalFormat = new DecimalFormat("#.0");
            dto.setRating(decimalFormat.format(ratingWithOneDecimal.floatValue()));

            BigDecimal ratingWithNoDecimal = rating.setScale(0, RoundingMode.HALF_UP);
            BigDecimal starSuffixNumber = ratingWithNoDecimal.divide(new BigDecimal(2)).multiply(new BigDecimal(10));
            decimalFormat = new DecimalFormat("00");
            String starClassName = decimalFormat.format(starSuffixNumber.longValue());
            dto.setStarClassName(starClassName);
        } else {
            //XXX 修改硬编码
            dto.setStarClassName("00");
        }
    }

    private void setRatingPercentageList(BookItemDto dto, List<Map<String, Object>> ratingCountGroupByScore) {
        Integer totalRatingCount = Integer.valueOf(dto.getRatingCount());
        if(totalRatingCount != 0) {
            dto.setRatingPercentageList(this.createPercentageList(ratingCountGroupByScore, totalRatingCount));
        }
    }

    private void setRatingPowerWidthPercentageList(BookItemDto dto, List<Map<String, Object>> ratingCountGroupByScore) {
        Integer totalRatingCount = Integer.valueOf(dto.getRatingCount());
        if(totalRatingCount != 0) {
            long maxCount = 0;
            for(Map<String, Object> ratingCountMap : ratingCountGroupByScore) {
                long ratingCount = (long)ratingCountMap.get("ratingCount");
                if(ratingCount > maxCount) {
                    maxCount = ratingCount;
                }
            }
            dto.setRatingPowerWidthPercentageList(this.createPercentageList(ratingCountGroupByScore, (int)maxCount));
        }
    }

    private List<String> createPercentageList(List<Map<String, Object>> ratingCountGroupByScore, Integer divisor) {
        List<String> ratingPercentageList = new ArrayList<>(5);
        for(int i = 0; i < 5; i++) {
            String zeroPercentage = "0.0%";
            ratingPercentageList.add(zeroPercentage);
        }
        for(Map<String, Object> ratingCountGroupByScoreMap : ratingCountGroupByScore) {
            BigDecimal singleRatingCount = new BigDecimal((Long)ratingCountGroupByScoreMap.get("ratingCount"));
            BigDecimal totalRatingCountBigDecimal = new BigDecimal(divisor);
            DecimalFormat decimalFormat = new DecimalFormat("#.0%");
            String ratingPercentageGroupByScore = decimalFormat.format(singleRatingCount.divide(totalRatingCountBigDecimal, 3, RoundingMode.HALF_UP).doubleValue());
            Integer score = (Integer)ratingCountGroupByScoreMap.get("score");
            // index的算法
            // (score / 2 - 1) 分数分别有2，4，6，8，10五种情况，List总长度是5，把五种情况的值放在List的index为0到4的五个元素中
            // 4 - (score / 2 - 1) 把顺序反过来
            ratingPercentageList.set(4 - (score / 2 - 1), ratingPercentageGroupByScore);
        }
        return ratingPercentageList;
    }
}
