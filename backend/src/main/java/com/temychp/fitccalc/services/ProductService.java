package com.temychp.fitccalc.services;

import com.temychp.fitccalc.models.product.Product;
import com.temychp.fitccalc.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findOne(Long id) {
        Optional<Product> foundBook = productRepository.findById(id);
        return foundBook.orElse(null);
    }

    @Transactional
    public void save(Product product) {
        productRepository.save(product);
    }

    @Transactional
    public void update(Long id, Product updatedProduct) {
        updatedProduct.setId(id);
        productRepository.save(updatedProduct);
    }

    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    public List<Product> showProductsStartingWith(String startString) {
        return productRepository.findByNameStartingWithIgnoreCase(startString);
    }

}