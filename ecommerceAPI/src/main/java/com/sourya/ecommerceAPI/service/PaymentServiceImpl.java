package com.sourya.ecommerceAPI.service;

import com.sourya.ecommerceAPI.entity.AuthorizationEntity;
import com.sourya.ecommerceAPI.model.PaymentReq;
import com.sourya.ecommerceAPI.repository.OrderRepository;
import com.sourya.ecommerceAPI.repository.PaymentRepository;

import java.util.Optional;
import java.util.UUID;

public class PaymentServiceImpl implements PaymentService{

    private PaymentRepository repository;
    private OrderRepository orderRepo;

    public PaymentServiceImpl(PaymentRepository repository, OrderRepository orderRepo) {
        this.repository = repository;
        this.orderRepo = orderRepo;
    }

    @Override
    public Optional<AuthorizationEntity> authorize(PaymentReq paymentReq) {
        return Optional.empty();
    }

    @Override
    public Optional<AuthorizationEntity> getOrdersPaymentAuthorization(String orderId) {
        return orderRepo.findById(UUID.fromString(orderId)).map(oe -> oe.getAuthorizationEntity());
    }
}
