package com.dpbinh.softbrain.dto;

import java.io.Serializable;

public class UpdateAuthorRequest implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UpdateAuthorRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
