package com.sourya.ecommerceAPI.repository;

import com.sourya.ecommerceAPI.entity.AuthorizationEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AuthorizationRepository extends CrudRepository<AuthorizationEntity, UUID> {
}
