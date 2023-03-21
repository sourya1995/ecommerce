package com.sourya.ecommerceAPI.service;

import com.sourya.ecommerceAPI.entity.ProductEntity;
import com.sourya.ecommerceAPI.repository.ProductRepository;

import java.util.Optional;
import java.util.UUID;

public class ProductServiceImpl implements ProductService{

    private ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<ProductEntity> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public Optional<ProductEntity> getProduct(String id) {
        return repository.findById(UUID.fromString(id));
    }
}
