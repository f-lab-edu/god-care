package com.godcare.common.dto;

import lombok.Getter;

@Getter
public class ResisterProductResponse {
    private Long productId;
    public ResisterProductResponse(Long productId) {
        this.productId = productId;
    }
}
