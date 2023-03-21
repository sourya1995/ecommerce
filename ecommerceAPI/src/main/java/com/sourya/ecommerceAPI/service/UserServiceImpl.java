package com.sourya.ecommerceAPI.service;

import com.sourya.ecommerceAPI.entity.AddressEntity;
import com.sourya.ecommerceAPI.entity.CardEntity;
import com.sourya.ecommerceAPI.entity.UserEntity;
import com.sourya.ecommerceAPI.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

public class UserServiceImpl implements UserService{

    private UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteCustomerById(String id) {
        repository.deleteById(UUID.fromString(id));
    }

    @Override
    public Optional<Iterable<AddressEntity>> getAddressesByCustomerId(String id) {
        return repository.findById(UUID.fromString(id)).map(UserEntity::getAddresses);
    }

    @Override
    public Iterable<UserEntity> getAllCustomers() {
        return repository.findAll();
    }

    @Override
    public Optional<CardEntity> getCardByCustomerID() {
        return Optional.of(repository.findById(UUID.fromString(id)).map(UserEntity::getCards).get().get(0));
    }

    @Override
    public Optional<UserEntity> getCustomerById() {
        return repository.findById(UUID.fromString(id));
    }
}
