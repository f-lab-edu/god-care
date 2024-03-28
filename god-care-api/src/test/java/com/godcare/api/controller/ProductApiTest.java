package com.godcare.api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.godcare.api.service.ProductService;
import com.godcare.api.vo.Response;
import com.godcare.common.dto.ResisterProductRequest;
import com.godcare.api.entity.Product;
import com.godcare.common.dto.ResisterProductResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ProductController.class)
public class ProductApiTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private ProductService productService;

    @Test
    @DisplayName("상품 등록 (컨트롤러 단위) 테스트")
    void uploadProductTest() throws Exception {
        // ResisterProductRequest -> Product 변환
        ResisterProductRequest request = new ResisterProductRequest("main.img", 1L);
        Product productToSave = Product.toProduct(request);

        // ProductService가 저장된 Product를 반환하도록 설정
        productToSave.assignId(1L);  // 테스트에서 기대하는 id 설정
        when(productService.addProduct(any(Product.class))).thenReturn(productToSave);

        // 요청으로 보낼 dto
        String content = new ObjectMapper().writeValueAsString(request);

        // 해당 url로 post 요청
        MvcResult mvcResult = mvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(print())
                .andReturn();

        // response 객체 반환
        Response<ResisterProductResponse> response = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<Response<ResisterProductResponse>>() {
        });

        // 검증
        assertEquals(response.getSuccess(), true);
        assertEquals(response.getData().getProductId(), 1L);

        // Service 메서드 호출 여부 확인
        verify(productService, times(1)).addProduct(any(Product.class));
    }
}
