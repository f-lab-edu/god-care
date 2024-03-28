package com.godcare.api.service;


import com.godcare.api.entity.Product;
import com.godcare.api.repository.ProductRepository;
import com.godcare.common.dto.ResisterProductRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
@Slf4j
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;

    @DisplayName("상품을 등록한다")
    @Test
    void viewProduct(){
        // given: 상품등록위한 준비과정
        String mainImg = "vitamin.img";
        Long categoryId = 3L;

        ResisterProductRequest request = new ResisterProductRequest(mainImg, categoryId);
        Product product = Product.toProduct(request);

        // mocking
        given(productRepository.save(any())).willReturn(product);

        // when: 실제로 상품을 저장
        Product savedProduct = productService.addProduct(product);

        // then: 상품저장 검증
        assertNotNull(savedProduct);
    }
}
