package com.oc.greenbean.vo;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Pagination {
    private int singleSize;
    private int startIndex;
    private int totalItemsCount;

    public Pagination(int singleSize, int startIndex, int totalItemsCount) {
        this.singleSize = singleSize;
        this.startIndex = startIndex;
        this.totalItemsCount = totalItemsCount;
    }

    public int getSingleSize() {
        return singleSize;
    }

    public void setSingleSize(int singleSize) {
        this.singleSize = singleSize;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getTotalItemsCount() {
        return totalItemsCount;
    }

    public void setTotalItemsCount(int totalItemsCount) {
        this.totalItemsCount = totalItemsCount;
    }

    public int getNumber() {
        int number = 0;
        if(singleSize != 0) {
            number = startIndex / singleSize + 1;
        }
        return number;
    }

    public int getTotalPagesCount() {
        int count = 0;
        if(singleSize != 0) {
            count = totalItemsCount / singleSize;
            BigDecimal bigDecimalCount = new BigDecimal(count);
            bigDecimalCount = bigDecimalCount.setScale(0, RoundingMode.UP);
            count = bigDecimalCount.intValue();
        }
        return count;
    }
}
