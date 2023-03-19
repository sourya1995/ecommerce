package com.sourya.ecommerceAPI.repository;

import com.sourya.ecommerceAPI.entity.ShipmentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ShipmentRepository extends CrudRepository<ShipmentEntity, UUID> {
}
