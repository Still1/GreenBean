package com.oc.greenbean.spring.service;

import com.oc.greenbean.domain.Author;
import com.oc.greenbean.domain.Book;
import com.oc.greenbean.domain.Translator;
import com.oc.greenbean.dto.*;
import com.oc.greenbean.mybatis.mapper.BookMapper;
import com.oc.greenbean.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BookService {
    private BookMapper bookMapper;

    @Value("${greenbean.pagination.size}")
    private Integer paginationSize;

    @Value("${picturesPath}")
    private String picturesPath;

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

    public BookPageDto getBookPage(Integer bookId, Integer userId) {
        return this.getBookPageDto(bookId, userId);
    }

    @Transactional
    public void saveBook(BookDto bookDto) {
        Book book = this.generateBook(bookDto);
        this.bookMapper.insertBookBasicInfo(book);

        this.insertBookAuthor(bookDto.getAuthor(), bookDto.getId());
        this.insertBookTranslator(bookDto.getTranslator(), bookDto.getId());
    }

    //XXX 考虑重名作者与译者
    //XXX 处理作者与处理译者的代码重复
    private void insertBookAuthor(List<String> authors, Integer bookId) {
        List<Integer> authorIds = new ArrayList<>();
        for(String authorName : authors) {
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
            this.bookMapper.insertBookAuthor(bookId, authorIds);
        }
    }

    private void insertBookTranslator(List<String> translators, Integer bookId) {
        List<Integer> translatorIds = new ArrayList<>();
        for(String translatorName : translators) {
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
            this.bookMapper.insertBookTranslator(bookId, translatorIds);
        }
    }

    @Transactional
    public void updateBook(BookDto bookDto) {
        Book book = this.generateBook(bookDto);
        Integer bookId = bookDto.getId();
        // XXX 没有修改过的字段不需要更新
        this.bookMapper.updateBookBasicInfo(book);
        this.bookMapper.removeBookAuthor(bookId);
        this.insertBookAuthor(bookDto.getAuthor(), bookId);
        this.bookMapper.removeBookTranslator(bookId);
        this.insertBookTranslator(bookDto.getTranslator(), bookId);
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
        book.setId(bookDto.getId());
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
        this.setBookBriefBasicInfo(dto.getBookBriefBasicInfo(), book);

        Map<String, Object> ratingInfo = this.getBookRatingInfo(id);
        this.setBookBriefRatingInfo(dto.getBookBriefRatingInfo(), ratingInfo);
        return dto;
    }

    private BookPageDto getBookPageDto(Integer bookId, Integer userId) {
        BookPageDto dto = new BookPageDto();
        Book book = this.getBookBasicInfo(bookId);
        this.setBookBriefBasicInfo(dto.getBookBriefBasicInfo(), book);
        this.setBookDetailBasicInfo(dto.getBookDetailBasicInfo(), book);

        Map<String, Object> ratingInfo = this.getBookRatingInfo(bookId);
        this.setBookBriefRatingInfo(dto.getBookBriefRatingInfo(), ratingInfo);

        List<Map<String, Object>> ratingCountGroupByScore = this.getBookRatingCountGroupByScore(bookId);
        long totalRatingCount = (long)ratingInfo.get("ratingCount");
        this.setRatingPercentageList(dto.getBookDetailRatingInfo(), ratingCountGroupByScore, totalRatingCount);
        this.setRatingPowerWidthPercentageList(dto.getBookDetailRatingInfo(), ratingCountGroupByScore);

        Map<String, Object> userRating = this.getBookUserRatingInfo(bookId, userId);
        this.setBookUserRatingInfo(dto.getBookUserRatingInfo(), userRating);

        Integer bookCommentCount = this.getBookUserCommentCount(bookId);
        if(bookCommentCount > 0) {
            // XXX size配置化
            List<Map<String, Object>> userComment = this.getUserComment(bookId, 0, 5);
            this.setBookUserCommentInfo(dto, bookCommentCount, userComment);
        } else {
            this.setBookUserCommentInfo(dto, bookCommentCount);
        }

        // XXX 硬编码
        Integer userReadingCount = this.getBookReadCount(bookId, 1);
        Integer userReadCount = this.getBookReadCount(bookId, 2);
        if(userReadingCount + userReadCount > 0) {
            // XXX size配置化
            List<Map<String, Object>> userRead = this.getUserRead(bookId, 0, 5);
            this.setBookReadInfo(dto, userReadingCount, userReadCount, userRead);
        } else {
            this.setBookReadInfo(dto, userReadingCount, userReadCount);
        }

        return dto;
    }

    public BookDto getBookDto(Integer bookId) {
        Book book = this.getBookBasicInfo(bookId);
        return this.convertBookToBookDto(book);
    }

    private BookDto convertBookToBookDto(Book book) {
        return new BookDto(book);
    }

    private void setBookUserCommentInfo(BookPageDto bookPageDto, Integer commentCount, List<Map<String, Object>> rawUserComment) {
        BookUserCommentInfo bookUserCommentInfo = bookPageDto.getBookUserCommentInfo();
        bookUserCommentInfo.setCommentCount(commentCount);
        if(rawUserComment != null) {
            List<UserComment> userCommentList = new ArrayList<>();
            for(Map<String, Object> singleRawUserComment : rawUserComment) {
                UserComment userComment = new UserComment();
                userComment.setNickname((String)singleRawUserComment.get("nickname"));
                Object scoreObject = singleRawUserComment.get("score");
                if(scoreObject != null) {
                    Integer score = (Integer)scoreObject;
                    String starClassNameSuffix = this.convertScoreToStarClassNameSuffix(new BigDecimal(score));
                    userComment.setStarClassNameSuffix(starClassNameSuffix);
                }
                Timestamp time = (Timestamp)singleRawUserComment.get("time");
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                userComment.setCommentDate(dateFormat.format(time));
                userComment.setComment((String)singleRawUserComment.get("comment"));
                userCommentList.add(userComment);
            }
            bookUserCommentInfo.setUserCommentList(userCommentList);
        }
    }

    private void setBookReadInfo(BookPageDto bookPageDto, Integer userReadingCount, Integer userReadCount) {
        this.setBookReadInfo(bookPageDto, userReadingCount, userReadCount, null);
    }

    private void setBookReadInfo(BookPageDto bookPageDto, Integer userReadingCount, Integer userReadCount, List<Map<String, Object>> rawUserRead) {
        BookReadInfo bookReadInfo  = bookPageDto.getBookReadInfo();
        bookReadInfo.setReadingCount(userReadingCount);
        bookReadInfo.setReadCount(userReadCount);
        if(rawUserRead != null) {
            List<UserRead> userReadList = new ArrayList<>();
            for(Map<String, Object> singleRawUserRead : rawUserRead) {
                UserRead userRead = new UserRead();
                userRead.setAvatar((String)singleRawUserRead.get("avatar"));
                userRead.setNickname((String) singleRawUserRead.get("nickname"));
                userRead.setType((Integer)singleRawUserRead.get("type"));
                Timestamp time = (Timestamp)singleRawUserRead.get("time");
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                userRead.setReadDate(dateFormat.format(time));
                userReadList.add(userRead);
            }
            bookReadInfo.setUserRead(userReadList);
        }
    }

    private void setBookUserCommentInfo(BookPageDto bookPageDto, Integer commentCount) {
        this.setBookUserCommentInfo(bookPageDto, commentCount, null);
    }

    private Integer getBookUserCommentCount(Integer bookId) {
        return this.bookMapper.getBookCommentCount(bookId);
    }

    private Integer getBookReadCount(Integer bookId, Integer type) {
        return this.bookMapper.getBookReadCount(bookId, type);
    }

    private List<Map<String, Object>> getUserComment(Integer bookId, Integer start, Integer size) {
        return this.bookMapper.getUserComment(bookId, start, size);
    }

    private List<Map<String, Object>> getUserRead(Integer bookId, Integer start, Integer size) {
        return this.bookMapper.getUserRead(bookId, start, size);
    }

    private List<Map<String, Integer>> getSearchBooksIdInOnePage(String keyword, Integer start) {
        return this.bookMapper.getSearchBooksWithPagination(keyword, start, paginationSize);
    }

    private Book getBookBasicInfo(Integer id) {
        return this.bookMapper.getBookBasicInfo(id);
    }

    private Map<String, Object> getBookRatingInfo(Integer id) {
        return this.bookMapper.getBookRatingInfo(id);
    }

    private List<Map<String, Object>> getBookRatingCountGroupByScore(Integer id) {
        return this.bookMapper.getBookRatingCountGroupByScore(id);
    }

    private Map<String, Object> getBookUserRatingInfo(Integer bookId, Integer userId) {
        return this.bookMapper.getBookUserRatingInfo(bookId, userId);
    }

    private void setBookUserRatingInfo(BookUserRatingInfo bookUserRatingInfo, Map<String, Object> userRating) {
        if(userRating != null && !userRating.isEmpty()) {
            bookUserRatingInfo.setType((Integer)userRating.get("type"));
            Integer score = (Integer)userRating.get("score");
            if(score != null) {
                bookUserRatingInfo.setStar(score / 2);
            }
            Timestamp time = (Timestamp)userRating.get("time");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            bookUserRatingInfo.setTime(dateFormat.format(time));
            bookUserRatingInfo.setComment((String)userRating.get("comment"));
        }
    }

    private void setBookBriefBasicInfo(BookBriefBasicInfo bookBriefBasicInfo, Book book) {
        bookBriefBasicInfo.setId(String.valueOf(book.getId()));
        bookBriefBasicInfo.setBookName(book.getName());
        //XXX 判断是否为空
        bookBriefBasicInfo.setPrice(String.valueOf(book.getPrice()));
        bookBriefBasicInfo.setPublisher(book.getPublisher());
        bookBriefBasicInfo.setPicture(book.getPicture());
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
        bookBriefBasicInfo.setPublicationDate(stringBuilder.toString());
        bookBriefBasicInfo.setPrice(String.valueOf(book.getPrice()));

        //XXX 抽取处理作者与译者的重复部分
        stringBuilder = new StringBuilder();
        for(Author author : book.getAuthors()) {
            stringBuilder.append(author.getName());
            stringBuilder.append(" / ");
        }
        stringBuilder.delete(stringBuilder.length() - 3, stringBuilder.length());
        bookBriefBasicInfo.setAuthorName(stringBuilder.toString());

        List<Translator> translators = book.getTranslators();
        if(translators.size() > 0) {
            stringBuilder = new StringBuilder();
            for(Translator translator : translators) {
                stringBuilder.append(translator.getName());
                stringBuilder.append(" / ");
            }
            if(stringBuilder.length() > 0) {
                stringBuilder.delete(stringBuilder.length() - 3, stringBuilder.length());
            }
            bookBriefBasicInfo.setTranslatorName(stringBuilder.toString());
        }
    }

    private void setBookDetailBasicInfo(BookDetailBasicInfo bookDetailBasicInfo, Book book) {
        bookDetailBasicInfo.setSubtitle(book.getSubtitle());
        bookDetailBasicInfo.setOriginalName(book.getOriginalName());
        bookDetailBasicInfo.setPage(String.valueOf(book.getPage()));
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
        bookDetailBasicInfo.setBinding(bindingString);
        bookDetailBasicInfo.setIsbn(book.getIsbn());
        bookDetailBasicInfo.setContentIntro(this.separateParagraph(book.getContentIntro()));
        bookDetailBasicInfo.setAuthorIntro(this.separateParagraph(book.getAuthorIntro()));
        bookDetailBasicInfo.setDirectory(this.separateParagraph(book.getDirectory()));
    }

    private List<String> separateParagraph(String string) {
        List<String> stringList = null;
        if(StringUtils.isNotBlank(string)) {
            stringList = Arrays.asList(string.split("\\n+"));
        }
        return stringList;
    }

    private void setBookBriefRatingInfo(BookBriefRatingInfo bookBriefRatingInfo, Map<String, Object> ratingInfo) {
        Long ratingCount = (Long)ratingInfo.get("ratingCount");
        bookBriefRatingInfo.setRatingCount(String.valueOf(ratingCount.intValue()));
        if(ratingCount > 0) {
            BigDecimal rating = (BigDecimal)ratingInfo.get("rating");
            BigDecimal ratingWithOneDecimal = rating.setScale(1, RoundingMode.HALF_UP);
            DecimalFormat decimalFormat = new DecimalFormat("#.0");
            bookBriefRatingInfo.setRating(decimalFormat.format(ratingWithOneDecimal.floatValue()));

            String starClassName = this.convertScoreToStarClassNameSuffix(rating);
            bookBriefRatingInfo.setStarClassName(starClassName);
        } else {
            //XXX 修改硬编码
            bookBriefRatingInfo.setStarClassName("00");
        }
    }

    private String convertScoreToStarClassNameSuffix(BigDecimal score) {
        BigDecimal ratingWithNoDecimal = score.setScale(0, RoundingMode.HALF_UP);
        BigDecimal starSuffixNumber = ratingWithNoDecimal.divide(new BigDecimal(2), RoundingMode.HALF_UP).multiply(new BigDecimal(10));
        DecimalFormat decimalFormat = new DecimalFormat("00");
        return decimalFormat.format(starSuffixNumber.longValue());
    }

    private void setRatingPercentageList(BookDetailRatingInfo bookDetailRatingInfo, List<Map<String, Object>> ratingCountGroupByScore, long totalRatingCount) {
        if(totalRatingCount != 0) {
            bookDetailRatingInfo.setRatingPercentageList(this.createPercentageList(ratingCountGroupByScore, totalRatingCount));
        }
    }

    private void setRatingPowerWidthPercentageList(BookDetailRatingInfo bookDetailRatingInfo, List<Map<String, Object>> ratingCountGroupByScore) {
        if(ratingCountGroupByScore.size() != 0) {
            long maxCount = 0;
            for(Map<String, Object> ratingCountMap : ratingCountGroupByScore) {
                long ratingCount = (long)ratingCountMap.get("ratingCount");
                if(ratingCount > maxCount) {
                    maxCount = ratingCount;
                }
            }
            bookDetailRatingInfo.setRatingPowerWidthPercentageList(this.createPercentageList(ratingCountGroupByScore, maxCount));
        }
    }

    private List<String> createPercentageList(List<Map<String, Object>> ratingCountGroupByScore, long divisor) {
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

    public void addOrUpdateUserRating(UserRatingDto dto) {
        Integer bookId = dto.getBookId();
        Integer userId = dto.getUserId();
        if(this.isUserRatingExist(bookId, userId)) {
            this.bookMapper.updateUserRating(dto);
        } else {
            this.bookMapper.insertUserRating(dto);
        }
    }

    private boolean isUserRatingExist(Integer bookId, Integer userId) {
        int count = this.bookMapper.getUserRatingCount(bookId, userId);
        boolean result = false;
        if(count > 0) {
            result = true;
        }
        return result;
    }

    public void removeUserRating(Integer bookId, Integer userId) {
        // TODO 验证确实有这条评价数据 再删除
        this.bookMapper.removeUserRating(bookId, userId);
    }

    public void updatePicture(Integer bookId, MultipartFile picture) throws IOException {
        //TODO 验证表单信息
        String pictureFileOriginalFileName = picture.getOriginalFilename();
        String pictureFileExtension = pictureFileOriginalFileName.substring(pictureFileOriginalFileName.lastIndexOf('.'));
        String pictureFileName = UUID.randomUUID().toString() + pictureFileExtension;
        String userHomePath = System.getProperty("user.home").replaceAll("\\\\", "/");
        String picturesPath = userHomePath + this.picturesPath;
        File pictureFolder = new File(picturesPath + "/books/");
        if(!pictureFolder.exists()) {
            pictureFolder.mkdir();
        }
        File pictureFile = new File(pictureFolder, pictureFileName);
        //TODO 删除旧头像
        picture.transferTo(pictureFile.toPath());
        this.bookMapper.updatePicture(bookId, pictureFileName);
    }
}
