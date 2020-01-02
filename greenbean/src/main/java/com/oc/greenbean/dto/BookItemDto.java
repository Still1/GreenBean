package com.oc.greenbean.dto;

import java.util.List;

public class BookItemDto {
    private String id;
    private String bookName;
    private String starClassName;
    private String rating;
    private String ratingCount;
    private String authorName;
    private String translatorName;
    private String publisher;
    private String publicationDate;
    private String price;
    private String picture;
    private String originalName;
    private String page;
    private String binding;
    private String isbn;
    private String contentIntro;
    private String authorIntro;
    private String directory;
    private List<String> ratingPercentageList;
    private List<String> ratingPowerWidthPercentageList;

    public List<String> getRatingPowerWidthPercentageList() {
        return ratingPowerWidthPercentageList;
    }

    public void setRatingPowerWidthPercentageList(List<String> ratingPowerWidthPercentageList) {
        this.ratingPowerWidthPercentageList = ratingPowerWidthPercentageList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(String ratingCount) {
        this.ratingCount = ratingCount;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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

    public List<String> getRatingPercentageList() {
        return ratingPercentageList;
    }

    public void setRatingPercentageList(List<String> ratingPercentageList) {
        this.ratingPercentageList = ratingPercentageList;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getStarClassName() {
        return starClassName;
    }

    public void setStarClassName(String starClassName) {
        this.starClassName = starClassName;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }



    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getTranslatorName() {
        return translatorName;
    }

    public void setTranslatorName(String translatorName) {
        this.translatorName = translatorName;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
