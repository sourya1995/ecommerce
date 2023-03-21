package com.sourya.ecommerceAPI.service;

import com.sourya.ecommerceAPI.entity.ShipmentEntity;
import com.sourya.ecommerceAPI.repository.ShipmentRepository;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.UUID;

public class ShipmentServiceImpl implements ShipmentService{

    private ShipmentRepository repository;

    public ShipmentServiceImpl(ShipmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<ShipmentEntity> getShipmentByOrderId(@Min(value = 1L, message = "Invalid shipment ID.") String id) {
        return repository.findAllById(List.of(UUID.fromString(id)));
    }
}
