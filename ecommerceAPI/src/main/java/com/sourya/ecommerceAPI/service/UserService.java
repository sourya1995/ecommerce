package com.sourya.ecommerceAPI.service;

import com.sourya.ecommerceAPI.entity.AddressEntity;
import com.sourya.ecommerceAPI.entity.CardEntity;
import com.sourya.ecommerceAPI.entity.UserEntity;

import java.util.Optional;

public interface UserService {
    public void deleteCustomerById(String id);
    public Optional<Iterable<AddressEntity>> getAddressesByCustomerId(String id);
    public Iterable<UserEntity> getAllCustomers();
    public Optional<CardEntity> getCardByCustomerID();
    public Optional<UserEntity> getCustomerById();
}
