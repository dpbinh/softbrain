package com.dpbinh.softbrain.dto;

import java.io.Serializable;

public class AddAuthorRequest implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AddAuthorRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
