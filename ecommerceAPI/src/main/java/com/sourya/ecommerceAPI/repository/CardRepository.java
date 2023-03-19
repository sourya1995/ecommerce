package com.sourya.ecommerceAPI.repository;

import com.sourya.ecommerceAPI.entity.CardEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CardRepository extends CrudRepository<CardEntity, UUID> {
}
