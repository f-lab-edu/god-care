package com.godcare.api.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PageResponse<T> {
    private List<T> values;
    private Boolean hasNext;

    public PageResponse(List<T> values, Boolean hasNext) {
        this.values = values;
        this.hasNext = hasNext;
    }
}
