package com.sourya.ecommerceAPI.repository;

import com.sourya.ecommerceAPI.entity.OrderItemEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OrderItemRepository extends CrudRepository<OrderItemEntity, UUID> {
}
