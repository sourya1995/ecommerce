package com.sourya.ecommerceAPI.service;

import com.sourya.ecommerceAPI.entity.OrderEntity;
import com.sourya.ecommerceAPI.exceptions.ResourceNotFoundException;
import com.sourya.ecommerceAPI.model.NewOrder;
import com.sourya.ecommerceAPI.repository.OrderRepository;
import org.apache.logging.log4j.util.Strings;

import javax.management.relation.RelationServiceNotRegisteredException;
import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class OrderServiceImpl implements OrderService{

    private OrderRepository repository;
    private OrderRepository userRepo;

    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<OrderEntity> addOrder(@Valid NewOrder newOrder) {
        if(Strings.isEmpty(newOrder.getCustomerId())) {
            throw new ResourceNotFoundException("Invalid Customer Id.");
        }
        if(Objects.isNull(newOrder.getAddress()) || Strings.isEmpty(newOrder.getAddress().getId())) {
            throw new ResourceNotFoundException("Invalid address id.");
        }
        if(Objects.isNull(newOrder.getCard()) || Strings.isEmpty(newOrder.getCard().getId())) {
            throw new ResourceNotFoundException("Invalid card id.");
        }
        return repository.insert(newOrder);
    }

    @Override
    public Iterable<OrderEntity> getOrdersByCustomerId(String customerId) {
        return repository.findByCustomerId(UUID.fromString(customerId));
    }

    @Override
    public Optional<OrderEntity> getByOrderId(String id) {
        return repository.findById(UUID.fromString(id));
    }
}
