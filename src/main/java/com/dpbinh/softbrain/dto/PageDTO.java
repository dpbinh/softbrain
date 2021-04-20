package com.dpbinh.softbrain.dto;

import java.util.List;

public class PageDTO<T>  {

    private Integer totalPage;
    private List<T> content;

    public PageDTO() {
    }

    public PageDTO(Integer totalPage, List<T> content) {
        this.totalPage = totalPage;
        this.content = content;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "PageDTO{" +
                "totalPage=" + totalPage +
                ", content=" + content +
                '}';
    }
}
