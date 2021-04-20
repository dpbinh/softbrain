package com.dpbinh.softbrain.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class BookSearchDTO extends PagingRequestDTO {

    @NotBlank
    @Valid
    private String authorName;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public String toString() {
        return "BookSearchDTO{" +
                "authorName='" + authorName + '\'' +
                '}';
    }
}
