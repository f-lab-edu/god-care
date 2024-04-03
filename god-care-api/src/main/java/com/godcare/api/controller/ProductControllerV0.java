package com.godcare.api.controller;

import com.godcare.api.service.ProductServiceV0;
import com.godcare.api.vo.PageResult;
import com.godcare.common.dto.*;
import com.godcare.api.vo.Response;
import com.godcare.api.entity.ProductV0;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.data.domain.PageRequest;


@RestController
@RequestMapping("/api/v0/products")
@RequiredArgsConstructor
@Api(tags = "상품 버전0 API")
public class ProductControllerV0 {

    private final ProductServiceV0 productService;
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "상품 등록 API ", description = "새로운 상품을 업로드하는 API")
    @PostMapping(path = "")
    public Response<ResisterProductResponse> addProduct(@RequestBody ResisterProductRequest resisterProductRequest) {

        ProductV0 savedProduct = productService.addProduct(resisterProductRequest);

        return Response.success(new ResisterProductResponse(savedProduct.getId()));
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "상품 조회 API ", description = "특정 상품 상세조회하는 API")
    @GetMapping(path = "/{product_id}")
    public Response<ViewProductResponse> viewProduct(@PathVariable(value = "product_id") Long productId) {
        ViewProductResponse viewProductResponse = productService.getProduct(productId);

        return Response.success(viewProductResponse);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "상품 전체 조회 API ", description = "전체 상품 조회하는 API")
    @GetMapping(path = "")
    public Response<List<ViewProductListResponse>> viewProductList() {

        List<ViewProductListResponse> productList = productService.getAllProducts();

        return Response.success(productList);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "상품 수정하는 API ", description = "등록된 상품 정보 수정하는 API")
    @PatchMapping(path = "/{product_id}")
    public Response<UpdateProductResponse> updateProduct(@PathVariable(value = "product_id") Long productId, @RequestBody UpdateProductRequest updateProductRequest) {

        ProductV0 product = productService.updateProduct(productId, updateProductRequest);

        return Response.success(new UpdateProductResponse(product.getId()));
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "상품 삭제 API ", description = "등록된 상품 삭제하는 API")
    @DeleteMapping(path = "/{product_id}")
    public Response<DeleteProductResponse> deleteProduct(@PathVariable(value = "product_id") Long productId) {

        productService.deleteProduct(productId);

        return Response.success(new DeleteProductResponse(productId));
    }

    private static final int DEFAULT_SIZE = 10;

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "커서 기반 조회 API", description = "커서 기반 조회 API")
    @GetMapping(path = "/cursor")
    public Response<PageResult<ViewProductListResponse>> getProducts(@RequestParam(required = false) Long cursorId, @RequestParam(required = false) Integer size) {
        if (size == null) size = DEFAULT_SIZE;
        PageResult<ViewProductListResponse> productList = productService.get(cursorId, PageRequest.of(0, size));
        return Response.success(productList);
    }
}
