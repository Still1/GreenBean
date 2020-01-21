package com.oc.greenbean.dto;

import com.oc.greenbean.vo.*;

public class BookPageDto {
    private BookBriefBasicInfo bookBriefBasicInfo = new BookBriefBasicInfo();
    private BookBriefRatingInfo bookBriefRatingInfo = new BookBriefRatingInfo();
    private BookDetailBasicInfo bookDetailBasicInfo = new BookDetailBasicInfo();
    private BookDetailRatingInfo bookDetailRatingInfo = new BookDetailRatingInfo();
    private BookUserRatingInfo bookUserRatingInfo = new BookUserRatingInfo();
    private BookUserCommentInfo bookUserCommentInfo = new BookUserCommentInfo();
    private BookReadInfo bookReadInfo = new BookReadInfo();

    public BookReadInfo getBookReadInfo() {
        return bookReadInfo;
    }

    public void setBookReadInfo(BookReadInfo bookReadInfo) {
        this.bookReadInfo = bookReadInfo;
    }

    public BookUserCommentInfo getBookUserCommentInfo() {
        return bookUserCommentInfo;
    }

    public void setBookUserCommentInfo(BookUserCommentInfo bookUserCommentInfo) {
        this.bookUserCommentInfo = bookUserCommentInfo;
    }

    public BookUserRatingInfo getBookUserRatingInfo() {
        return bookUserRatingInfo;
    }

    public void setBookUserRatingInfo(BookUserRatingInfo bookUserRatingInfo) {
        this.bookUserRatingInfo = bookUserRatingInfo;
    }

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
