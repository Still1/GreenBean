package com.oc.greenbean.domain;

public class Book {
    private Integer id;
    private String name;
    private String isbn;
    private String picture;
    private String publisher;
    private Integer publicationYear;
    private Float price;
    private Integer publicationMonth;
    private Integer publicationDay;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
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
