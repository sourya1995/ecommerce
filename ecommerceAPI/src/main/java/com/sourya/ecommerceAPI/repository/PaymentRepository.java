package com.sourya.ecommerceAPI.repository;

import com.sourya.ecommerceAPI.entity.PaymentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PaymentRepository extends CrudRepository<PaymentEntity, UUID> {
}
