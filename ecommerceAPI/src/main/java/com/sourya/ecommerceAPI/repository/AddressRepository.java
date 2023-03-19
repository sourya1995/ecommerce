package com.sourya.ecommerceAPI.repository;

import com.sourya.ecommerceAPI.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AddressRepository extends CrudRepository<AddressEntity, UUID> {
}
