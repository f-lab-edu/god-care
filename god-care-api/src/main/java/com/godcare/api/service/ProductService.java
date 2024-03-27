package com.godcare.api.service;

import com.godcare.api.entity.Product;
import com.godcare.api.repository.ProductRepository;
import com.godcare.common.dto.ResisterProductRequest;
import com.godcare.common.dto.UpdateProductRequest;
import com.godcare.common.dto.ViewProductListResponse;
import com.godcare.common.dto.ViewProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    // 상품등록
    public Product addProduct(ResisterProductRequest request) {
        Product savedProduct = productRepository.save(Product.from(request));
        return savedProduct;
    }
    // 상품조회
    public ViewProductResponse getProduct(Long productId) {
        Product product = productRepository.findById(productId);
        return new ViewProductResponse(product.getId(), product.getMainImg(), product.getCategoryId());
    }
    // 상품전체조회
    public List<ViewProductListResponse> getAllProducts() {
        Collection<Product> values = productRepository.values();
        return values.stream().map(product -> new ViewProductListResponse(product.getMainImg(), product.getCategoryId())).collect(Collectors.toList());
    }
    // 상품수정
    public Product updateProduct(Long productId, UpdateProductRequest updateProductRequest) {
        // 상품 찾기
        Product product = productRepository.findById(productId);
        // 해당 엔티티의 내용을 수정 사항과 맵핑하여 저장
        product.update(updateProductRequest.getMainImg(), updateProductRequest.getCategoryId());
        Product updatedProduct = productRepository.saveUpdated(product);
        return updatedProduct;
    }

    // 상품삭제
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}
