package com.sourya.ecommerceAPI.repository;

import com.sourya.ecommerceAPI.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<UserEntity, UUID> {
}
