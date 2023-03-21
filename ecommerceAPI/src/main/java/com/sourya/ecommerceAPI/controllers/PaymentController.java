package com.sourya.ecommerceAPI.controllers;

import com.sourya.ecommerceAPI.api.PaymentApi;
import com.sourya.ecommerceAPI.hateoas.PaymentRepresentationModelAssembler;
import com.sourya.ecommerceAPI.model.Authorization;
import com.sourya.ecommerceAPI.model.PaymentReq;
import com.sourya.ecommerceAPI.service.PaymentService;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class PaymentController implements PaymentApi {

    private PaymentService service;
    private final PaymentRepresentationModelAssembler assembler;

    public PaymentController(PaymentService service, PaymentRepresentationModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @Override
    public ResponseEntity<Authorization> authorize(@Valid PaymentReq paymentReq) {
        return PaymentApi.super.authorize(paymentReq);
    }

    @Override
    public ResponseEntity<List<Authorization>> getOrdersPaymentAuthorization(@NotNull @Valid String id) {
        return PaymentApi.super.getOrdersPaymentAuthorization(id);
    }
}
