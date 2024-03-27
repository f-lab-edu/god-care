package com.godcare.common.dto;

import lombok.Getter;

@Getter
public class DeleteProductResponse {
    private Long productId;
    public DeleteProductResponse(Long productId) {
        this.productId = productId;
    }
}
