package com.oc.greenbean.dto;

import com.oc.greenbean.vo.BookBriefBasicInfo;
import com.oc.greenbean.vo.BookBriefRatingInfo;

public class BookItemDto {
    private BookBriefBasicInfo bookBriefBasicInfo = new BookBriefBasicInfo();
    private BookBriefRatingInfo bookBriefRatingInfo = new BookBriefRatingInfo();

    public BookBriefBasicInfo getBookBriefBasicInfo() {
        return bookBriefBasicInfo;
    }

    public void setBookBriefBasicInfo(BookBriefBasicInfo bookBriefBasicInfo) {
        this.bookBriefBasicInfo = bookBriefBasicInfo;
    }

    public BookBriefRatingInfo getBookBriefRatingInfo() {
        return bookBriefRatingInfo;
    }

    public void setBookBriefRatingInfo(BookBriefRatingInfo bookBriefRatingInfo) {
        this.bookBriefRatingInfo = bookBriefRatingInfo;
    }
}
