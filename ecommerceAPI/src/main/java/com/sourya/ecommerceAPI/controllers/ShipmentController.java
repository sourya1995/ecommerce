package com.sourya.ecommerceAPI.controllers;

import com.sourya.ecommerceAPI.api.ShippingApi;
import com.sourya.ecommerceAPI.hateoas.ShipmentRepresentationModelAssembler;
import com.sourya.ecommerceAPI.model.Authorization;
import com.sourya.ecommerceAPI.model.Shipment;
import com.sourya.ecommerceAPI.model.ShippingReq;
import com.sourya.ecommerceAPI.service.ShipmentService;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ShipmentController implements ShippingApi {

    private ShipmentService service;
    private final ShipmentRepresentationModelAssembler assembler;


    public ShipmentController(ShipmentService service, ShipmentRepresentationModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @Override
    public ResponseEntity<List<Shipment>> getShipmentByOrderId(String id) {
        return ResponseEntity.ok(assembler.toListModel(service.getShipmentByOrderId(id)));
    }

    @Override
    public ResponseEntity<Authorization> shipOrder(ShippingReq shippingReq) {
        return ShippingApi.super.shipOrder(shippingReq);
    }
}
