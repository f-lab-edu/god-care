package com.godcare.api.repository;

import com.godcare.api.entity.Product;
import org.springframework.stereotype.Repository;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository extends ConcurrentHashMap<Long, Product>{
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
    public void deleteById(Long productId) {
        remove(productId);
    }

    public Product saveUpdated(Product product) {
        put(product.getId(), product);
        return product;
    }
}
