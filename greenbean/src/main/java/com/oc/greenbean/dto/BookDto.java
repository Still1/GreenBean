package com.oc.greenbean.dto;

import com.oc.greenbean.domain.Author;
import com.oc.greenbean.domain.Book;
import com.oc.greenbean.domain.Translator;

import java.util.ArrayList;
import java.util.List;

public class BookDto {
    private Integer id;
    private String name;
    private String isbn;
    private List<String> author;
    private List<String> translator;
    private Float price;
    private String publisher;
    private Integer publicationYear;
    private Integer publicationMonth;
    private Integer publicationDay;
    private String subtitle;
    private String originalName;
    private Integer binding;
    private Integer page;
    private String contentIntro;
    private String authorIntro;
    private String directory;
    private String picture;

    public BookDto() {
    }

    public BookDto(Book book) {
        // XXX 反射 apache commons
        this.id = book.getId();
        this.name = book.getName();
        this.isbn = book.getIsbn();
        this.price = book.getPrice();
        this.publisher = book.getPublisher();
        this.publicationYear = book.getPublicationYear();
        this.publicationMonth = book.getPublicationMonth();
        this.publicationDay = book.getPublicationDay();
        this.subtitle = book.getSubtitle();
        this.originalName = book.getOriginalName();
        this.binding = book.getBinding();
        this.page = book.getPage();
        this.contentIntro = book.getContentIntro();
        this.authorIntro = book.getAuthorIntro();
        this.directory = book.getDirectory();
        this.picture = book.getPicture();
        this.author = new ArrayList<>();
        this.translator = new ArrayList<>();
        for(Author author : book.getAuthors()) {
            this.author.add(author.getName());
        }
        for(Translator translator : book.getTranslators()) {
            this.translator.add(translator.getName());
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

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

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public List<String> getTranslator() {
        return translator;
    }

    public void setTranslator(List<String> translator) {
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
