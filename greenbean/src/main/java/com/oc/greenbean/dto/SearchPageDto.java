package com.oc.greenbean.dto;

import com.oc.greenbean.vo.Pagination;

import java.util.List;

public class SearchPageDto {
    private List<SearchBookItemDto> bookItems;
    private Pagination pagination;

    public List<SearchBookItemDto> getBookItems() {
        return bookItems;
    }

    public void setBookItems(List<SearchBookItemDto> bookItems) {
        this.bookItems = bookItems;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
