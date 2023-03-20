package com.sourya.ecommerceAPI.service;

import com.sourya.ecommerceAPI.entity.CardEntity;
import com.sourya.ecommerceAPI.entity.UserEntity;
import com.sourya.ecommerceAPI.model.AddCardReq;
import com.sourya.ecommerceAPI.repository.CardRepository;
import com.sourya.ecommerceAPI.repository.UserRepository;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

public class CardServiceImpl implements CardService{

    private CardRepository repository;
    private UserRepository userRepo;

    public CardServiceImpl(CardRepository repository, UserRepository userRepo) {
        this.repository = repository;
        this.userRepo = userRepo;
    }

    @Override
    public void deleteCardById(String id) {
        repository.deleteById(UUID.fromString(id));
    }

    @Override
    public Iterable<CardEntity> getAllCards() {
        return repository.findAll();
    }

    @Override
    public Optional<CardEntity> getCardById(String id) {
        return repository.findById(UUID.fromString(id));
    }

    @Override
    public Optional<CardEntity> registerCard(@Valid AddCardReq addCardReq) {
        return Optional.of(repository.save(toEntity(addCardReq)));
    }

    private CardEntity toEntity(AddCardReq m){
        CardEntity e = new CardEntity();
        Optional<UserEntity> user = userRepo.findById(UUID.fromString((String) m.getUserId()));
        user.ifPresent(u -> e.setUser(u));
        return e.setNumber(m.getCardNumber()).setCvv(m.getCcv())
                .setExpires(m.getExpires());
    }

}
