package com.godcare.common.dto;


import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ViewProductListResponse {

    @NonNull
    private String mainImg;
    @NonNull
    private Long categoryId;

}
