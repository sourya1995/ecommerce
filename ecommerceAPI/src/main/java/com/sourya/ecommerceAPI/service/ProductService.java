package com.sourya.ecommerceAPI.service;

import com.sourya.ecommerceAPI.entity.ProductEntity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface ProductService {
    @NotNull Iterable<ProductEntity> getAllProducts();
    Optional<ProductEntity> getProduct(@Min(value = 1L, message= "Invalid product ID.") String id);
}
