package com.sourya.ecommerceAPI.repository;

import com.sourya.ecommerceAPI.entity.OrderEntity;
import com.sourya.ecommerceAPI.model.NewOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends CrudRepository<OrderEntity, UUID> {
    @Query("select o from OrderEntity o join o.userEntity u where u.id = :customerId")
    public Iterable<OrderEntity> findByCustomerId(@Param("customerId") UUID customerId);

    Optional<OrderEntity> insert(NewOrder newOrder);
}
