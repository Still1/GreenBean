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
    private String subtitle;
    private String originalName;
    private Integer binding;
    private Integer page;
    private String contentIntro;
    private String authorIntro;
    private String directory;

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public Integer getBinding() {
        return binding;
    }

    public void setBinding(Integer binding) {
        this.binding = binding;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getContentIntro() {
        return contentIntro;
    }

    public void setContentIntro(String contentIntro) {
        this.contentIntro = contentIntro;
    }

    public String getAuthorIntro() {
        return authorIntro;
    }

    public void setAuthorIntro(String authorIntro) {
        this.authorIntro = authorIntro;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

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
