package com.sourya.ecommerceAPI.repository;

import com.sourya.ecommerceAPI.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ProductRepository extends CrudRepository<ProductEntity, UUID> {

}
