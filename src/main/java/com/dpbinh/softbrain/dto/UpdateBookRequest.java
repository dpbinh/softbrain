package com.dpbinh.softbrain.dto;

import java.io.Serializable;

public class UpdateBookRequest implements Serializable {
    private String title;
    private Integer totalPage;
    private Long publishedDate;
    private Integer authorId;

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

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "UpdateBookRequest{" +
                "title='" + title + '\'' +
                ", totalPage=" + totalPage +
                ", publishedDate=" + publishedDate +
                ", authorId=" + authorId +
                '}';
    }
}
