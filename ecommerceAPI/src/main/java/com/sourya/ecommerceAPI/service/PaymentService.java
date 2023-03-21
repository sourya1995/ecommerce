package com.sourya.ecommerceAPI.service;

import com.sourya.ecommerceAPI.entity.AuthorizationEntity;
import com.sourya.ecommerceAPI.model.PaymentReq;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface PaymentService {
    public Optional<AuthorizationEntity> authorize(@Valid PaymentReq paymentReq);
    public Optional<AuthorizationEntity> getOrdersPaymentAuthorization(@NotNull String orderId);
}
