package com.oc.greenbean.vo;

import java.util.List;

public class BookDetailRatingInfo {
    private List<String> ratingPercentageList;
    private List<String> ratingPowerWidthPercentageList;

    public List<String> getRatingPercentageList() {
        return ratingPercentageList;
    }

    public void setRatingPercentageList(List<String> ratingPercentageList) {
        this.ratingPercentageList = ratingPercentageList;
    }

    public List<String> getRatingPowerWidthPercentageList() {
        return ratingPowerWidthPercentageList;
    }

    public void setRatingPowerWidthPercentageList(List<String> ratingPowerWidthPercentageList) {
        this.ratingPowerWidthPercentageList = ratingPowerWidthPercentageList;
    }
}
