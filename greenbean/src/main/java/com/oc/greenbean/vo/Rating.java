package com.oc.greenbean.vo;

import java.util.List;

public class Rating {
    private String rating;
    private Integer ratingCount;
    private List<String> percentageList;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }

    public List<String> getPercentageList() {
        return percentageList;
    }

    public void setPercentageList(List<String> percentageList) {
        this.percentageList = percentageList;
    }
}
