package com.sourya.ecommerceAPI.controllers;

import com.sourya.ecommerceAPI.api.CardApi;
import com.sourya.ecommerceAPI.hateoas.CardRepresentationModelAssembler;
import com.sourya.ecommerceAPI.model.AddCardReq;
import com.sourya.ecommerceAPI.model.Card;
import com.sourya.ecommerceAPI.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

public class CardController implements CardApi {

    private CardService service;
    private final CardRepresentationModelAssembler assembler;


    public CardController(CardService service, CardRepresentationModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @Override
    public ResponseEntity<Void> deleteCardById(String id) {
        service.deleteCardById(id);
        return accepted().build();
    }

    @Override
    public ResponseEntity<List<Card>> getAllCards() {
        return ok(assembler.toListModel(service.getAllCards()));
    }

    @Override
    public ResponseEntity<Card> getCardById(String id) {
        return service.getCardById(id).map(assembler::toModel)
                .map(ResponseEntity::ok).orElse(notFound().build());
    }

    @Override
    public ResponseEntity<Card> registerCard(@Valid AddCardReq addCardReq) {
        return status(HttpStatus.CREATED).body(service.registerCard(addCardReq).map(assembler::toModel).get());
    }
}
