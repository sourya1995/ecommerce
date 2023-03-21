package com.sourya.ecommerceAPI.service;

import com.sourya.ecommerceAPI.entity.ShipmentEntity;

import javax.validation.constraints.Min;

public interface ShipmentService {
    public Iterable<ShipmentEntity> getShipmentByOrderId(@Min(value = 1L, message = "Invalid product ID.") String id);
}
