package com.oc.greenbean.vo;

import java.util.List;

public class BookUserCommentInfo {
    private List<UserComment> userCommentList;
    private Integer commentCount;

    public List<UserComment> getUserCommentList() {
        return userCommentList;
    }

    public void setUserCommentList(List<UserComment> userCommentList) {
        this.userCommentList = userCommentList;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }
}
