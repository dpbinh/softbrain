package com.dpbinh.softbrain.dto;

import com.dpbinh.softbrain.entity.Author;

import java.io.Serializable;

public class AuthorDTO implements Serializable {
    private Integer id;
    private String name;

    public AuthorDTO() {
    }

    public AuthorDTO(Author author) {
        this.id = author.getId();
        this.name = author.getName();
    }

    public AuthorDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "AuthorDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
