package com.dpbinh.softbrain.dto;

import com.dpbinh.softbrain.entity.Author;
import com.dpbinh.softbrain.entity.Book;

import java.io.Serializable;
import java.util.Optional;

public class BookDTO implements Serializable {

    private Long id;
    private String title;
    private Integer totalPage;
    private Long publishedDate;
    private String authorName;

    public BookDTO() {
    }

    public BookDTO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.totalPage = book.getTotalPage();
        this.publishedDate = book.getPublishedDate().toEpochMilli();
        this.authorName = Optional.ofNullable(book.getAuthor()).orElse(new Author()).getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Long getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Long publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "title='" + title + '\'' +
                ", totalPage=" + totalPage +
                ", publishedDate='" + publishedDate + '\'' +
                ", authorName='" + authorName + '\'' +
                '}';
    }
}
