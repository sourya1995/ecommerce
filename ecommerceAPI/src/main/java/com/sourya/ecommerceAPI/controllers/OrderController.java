package com.sourya.ecommerceAPI.controllers;

import com.sourya.ecommerceAPI.api.OrderApi;
import com.sourya.ecommerceAPI.hateoas.OrderRepresentationModelAssembler;
import com.sourya.ecommerceAPI.model.NewOrder;
import com.sourya.ecommerceAPI.model.Order;
import com.sourya.ecommerceAPI.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

public class OrderController implements OrderApi {

    private final OrderRepresentationModelAssembler assembler;
    private OrderService service;

    public OrderController(OrderRepresentationModelAssembler assembler, OrderService service) {
        this.assembler = assembler;
        this.service = service;
    }

    @Override
    public ResponseEntity<List<Order>> addOrder(@Valid NewOrder newOrder) {
        return service.addOrder(newOrder).map(assembler::toModel).map(ResponseEntity::ok)
                .orElse(notFound().build());
    }

    @Override
    public ResponseEntity<List<Order>> getOrdersByCustomerId(@NotNull @Valid String customerId) {
        return ok(assembler.toListModel(service.getOrdersByCustomerId(customerId)));
    }

    @Override
    public ResponseEntity<Order> getOrdersByOrderId(String id) {
        return service.getByOrderId(id).map(assembler::toModel).map(ResponseEntity::ok)
                .orElse(notFound().build());
    }
}
