package com.godcare.common.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ViewProductResponse {

    @NonNull
    private Long productId;
    @NonNull
    private String mainImg;
    @NonNull
    private Long categoryId;

}
