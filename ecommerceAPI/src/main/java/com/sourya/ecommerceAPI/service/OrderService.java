package com.sourya.ecommerceAPI.service;

import com.sourya.ecommerceAPI.entity.OrderEntity;
import com.sourya.ecommerceAPI.model.NewOrder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface OrderService {
    public Optional<OrderEntity> addOrder(@Valid NewOrder newOrder);
    public Iterable<OrderEntity> getOrdersByCustomerId(@NotNull @Valid String customerId);
    public Optional<OrderEntity> getByOrderId(String id);
}
