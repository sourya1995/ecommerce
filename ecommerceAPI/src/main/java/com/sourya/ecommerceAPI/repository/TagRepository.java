package com.sourya.ecommerceAPI.repository;

import com.sourya.ecommerceAPI.entity.TagEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TagRepository extends CrudRepository<TagEntity, UUID> {
}
