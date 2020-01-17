package com.oc.greenbean.vo;

public class UserComment {
    private String nickname;
    private String starClassNameSuffix;
    private String commentDate;
    private String comment;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getStarClassNameSuffix() {
        return starClassNameSuffix;
    }

    public void setStarClassNameSuffix(String starClassNameSuffix) {
        this.starClassNameSuffix = starClassNameSuffix;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
