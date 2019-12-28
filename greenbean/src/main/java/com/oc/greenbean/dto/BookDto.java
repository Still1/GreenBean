package com.oc.greenbean.dto;

import java.util.List;

public class BookDto {
    private String name;
    private String isbn;
    private List<Integer> author;
    private List<Integer> translator;
    private Float price;
    private String publisher;
    private Integer publicationYear;
    private Integer publicationMonth;
    private Integer publicationDay;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<Integer> getAuthor() {
        return author;
    }

    public void setAuthor(List<Integer> author) {
        this.author = author;
    }

    public List<Integer> getTranslator() {
        return translator;
    }

    public void setTranslator(List<Integer> translator) {
        this.translator = translator;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Integer getPublicationMonth() {
        return publicationMonth;
    }

    public void setPublicationMonth(Integer publicationMonth) {
        this.publicationMonth = publicationMonth;
    }

    public Integer getPublicationDay() {
        return publicationDay;
    }

    public void setPublicationDay(Integer publicationDay) {
        this.publicationDay = publicationDay;
    }
}
