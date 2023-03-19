package com.sourya.ecommerceAPI.repository;

import com.sourya.ecommerceAPI.entity.OrderEntity;
import com.sourya.ecommerceAPI.model.NewOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrderRepositoryExt {
    Optional<OrderEntity> insert(NewOrder m);
}
