package com.godcare.common.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResisterProductRequest {

    @NonNull
    private String mainImg;  // 메인 이미지(썸네일)
    @NonNull
    private Long categoryId; // 카테고리 아이디

}
