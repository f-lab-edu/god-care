package com.godcare.common.dto;

import lombok.Getter;

@Getter
public class UpdateProductResponse {
    private Long productId;
    public UpdateProductResponse(Long productId) {
        this.productId = productId;
    }
}
