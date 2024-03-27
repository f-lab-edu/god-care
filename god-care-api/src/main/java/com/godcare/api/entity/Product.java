package com.godcare.api.entity;

import com.godcare.common.dto.ResisterProductRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Product {

    private String mainImg;
    private Long categoryId;
    private Long id;

    public void assignId(Long id) {
        this.id = id;
    }
    public static Product from(ResisterProductRequest request) {
        String mainImg = request.getMainImg();
        Long categoryId = request.getCategoryId();
        Long id = null;
        return new Product(mainImg, categoryId, id);
    }

    public void update(String mainImg, Long categoryId) {
      this.mainImg = mainImg;
      this.categoryId = categoryId;
    }
}
