package com.oc.greenbean.dto;

import com.oc.greenbean.vo.BookBriefBasicInfo;
import com.oc.greenbean.vo.BookBriefRatingInfo;
import com.oc.greenbean.vo.BookDetailBasicInfo;
import com.oc.greenbean.vo.BookDetailRatingInfo;

public class BookPageDto {
    private BookBriefBasicInfo bookBriefBasicInfo = new BookBriefBasicInfo();
    private BookBriefRatingInfo bookBriefRatingInfo = new BookBriefRatingInfo();
    private BookDetailBasicInfo bookDetailBasicInfo = new BookDetailBasicInfo();
    private BookDetailRatingInfo bookDetailRatingInfo = new BookDetailRatingInfo();

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

    public BookDetailBasicInfo getBookDetailBasicInfo() {
        return bookDetailBasicInfo;
    }

    public void setBookDetailBasicInfo(BookDetailBasicInfo bookDetailBasicInfo) {
        this.bookDetailBasicInfo = bookDetailBasicInfo;
    }

    public BookDetailRatingInfo getBookDetailRatingInfo() {
        return bookDetailRatingInfo;
    }

    public void setBookDetailRatingInfo(BookDetailRatingInfo bookDetailRatingInfo) {
        this.bookDetailRatingInfo = bookDetailRatingInfo;
    }
}
