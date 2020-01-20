package com.oc.greenbean.vo;

import java.util.List;

public class BookReadInfo {
    // XXX 重构一下变量的命名
    private List<UserRead> userRead;
    private Integer readingCount;
    private Integer readCount;

    public List<UserRead> getUserRead() {
        return userRead;
    }

    public void setUserRead(List<UserRead> userRead) {
        this.userRead = userRead;
    }

    public Integer getReadingCount() {
        return readingCount;
    }

    public void setReadingCount(Integer readingCount) {
        this.readingCount = readingCount;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }
}
