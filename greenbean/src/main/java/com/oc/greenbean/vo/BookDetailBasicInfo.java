package com.oc.greenbean.vo;

import java.util.List;

public class BookDetailBasicInfo {
    private String subtitle;
    private String originalName;
    private String page;
    private String binding;
    private String isbn;
    private List<String> contentIntro;
    private List<String> authorIntro;
    private List<String> directory;

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

    public List<String> getContentIntro() {
        return contentIntro;
    }

    public void setContentIntro(List<String> contentIntro) {
        this.contentIntro = contentIntro;
    }

    public List<String> getAuthorIntro() {
        return authorIntro;
    }

    public void setAuthorIntro(List<String> authorIntro) {
        this.authorIntro = authorIntro;
    }

    public List<String> getDirectory() {
        return directory;
    }

    public void setDirectory(List<String> directory) {
        this.directory = directory;
    }
}
