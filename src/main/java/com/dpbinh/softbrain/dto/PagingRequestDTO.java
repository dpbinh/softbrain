package com.dpbinh.softbrain.dto;

import com.dpbinh.softbrain.config.Constant;

import javax.validation.constraints.Min;
import java.io.Serializable;

public class PagingRequestDTO implements Serializable {

    @Min(0)
    private Integer page = 0;

    @Min(1)
    private Integer limit = Constant.LIMIT_PAGE_SIZE;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "PagingRequestDTO{" +
                "page=" + page +
                ", limit=" + limit +
                '}';
    }
}
