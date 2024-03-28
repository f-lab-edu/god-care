package com.godcare.api.repository;

import com.godcare.api.entity.Product;
import com.godcare.common.dto.ViewProductListResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class ProductHashMapRepository extends ConcurrentHashMap<Long, Product>{
    private AtomicLong sequence = new AtomicLong(1L);
    public Product save(Product product) {
        product.assignId(sequence.getAndIncrement());
        put(product.getId(), product);
        return product;
    }
    public Product findById(Long productId) {
         Product found = get(productId);
        return found;
    }
    public List<ViewProductListResponse> findAll() {
        Collection<Product> values = values();
        return values.stream().map(product -> new ViewProductListResponse(product.getMainImg(), product.getCategoryId(), product.getId())).collect(Collectors.toList());
    }
    public void deleteById(Long productId) {
        remove(productId);
    }

    public Product saveUpdated(Product product) {
        put(product.getId(), product);
        return product;
    }

    public List<Product> findAllByOrderByIdDesc(Pageable pageable) {
        Result result = getResult(pageable);

        return entrySet().stream()
                .skip(result.start)
                .limit(result.end - result.start)
                .sorted(Map.Entry.<Long, Product>comparingByKey().reversed())
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
    public List<Product> findByIdLessThanOrderByIdDesc(Long id, Pageable pageable) {
        Result result = getResult(pageable);

        return entrySet().stream()
                .filter(entry -> entry.getKey() > id)
                .skip(result.start)
                .limit(result.end - result.start)
                .sorted(Map.Entry.<Long, Product>comparingByKey().reversed())
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public boolean existsByIdLessThan(Long lastId) {
        for (Long key : keySet()) {
            if (key > lastId) {
                return true;
            }
        }
        return false;
    }

    private Result getResult(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        int start = pageNumber * pageSize;
        int end = Math.min(start + pageSize, size());
        Result result = new Result(start, end);
        return result;
    }
    private class Result {
        private final int start;
        private final int end;

        private Result(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
