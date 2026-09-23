package com.example.shopsystem.service;

import com.example.shopsystem.model.Product;
import com.example.shopsystem.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll() { return productRepository.findAll(); }

    public List<Product> search(String q) {
        if (q == null || q.isBlank()) return findAll();
        return productRepository.findByNameContainingIgnoreCase(q);
    }

    public Product findById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));
    }

    public Product save(Product product) { return productRepository.save(product); }

    public void delete(String id) { productRepository.deleteById(id); }

    public long count() { return productRepository.count(); }

    public List<Product> lowStock() {
        return productRepository.findAll().stream()
                .filter(p -> p.getQuantity() != null && p.getReorderLevel() != null
                        && p.getQuantity() <= p.getReorderLevel())
                .toList();
    }

    public double totalInventoryValue() {
        return productRepository.findAll().stream()
                .filter(p -> p.getPrice() != null && p.getQuantity() != null)
                .mapToDouble(p -> p.getPrice() * p.getQuantity())
                .sum();
    }
}