package com.sourya.ecommerceAPI.controllers;

import com.sourya.ecommerceAPI.api.CustomerApi;
import com.sourya.ecommerceAPI.hateoas.AddressRepresentationModelAssembler;
import com.sourya.ecommerceAPI.hateoas.CardRepresentationModelAssembler;
import com.sourya.ecommerceAPI.hateoas.UserRepresentationModelAssembler;
import com.sourya.ecommerceAPI.model.Address;
import com.sourya.ecommerceAPI.model.Card;
import com.sourya.ecommerceAPI.model.User;
import com.sourya.ecommerceAPI.service.UserService;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

public class CustomerController implements CustomerApi {

    private final UserRepresentationModelAssembler assembler;
    private final AddressRepresentationModelAssembler addAssembler;
    private final CardRepresentationModelAssembler cardAssembler;
    private UserService service;

    public CustomerController(UserRepresentationModelAssembler assembler, AddressRepresentationModelAssembler addAssembler, CardRepresentationModelAssembler cardAssembler, UserService service) {
        this.assembler = assembler;
        this.addAssembler = addAssembler;
        this.cardAssembler = cardAssembler;
        this.service = service;
    }

    @Override
    public ResponseEntity<Void> deleteCustomerById(String id) {
        service.deleteCustomerById(id);
        return accepted().build();
    }

    @Override
    public ResponseEntity<List<Address>> getAddressesByCustomerId(String id) {
       return service.getAddressesByCustomerId(id).map(addAssembler::toListModel)
               .map(ResponseEntity::ok).orElse(notFound().build());
    }

    @Override
    public ResponseEntity<List<User>> getAllCustomers() {
        return ok(assembler.toListModel(service.getAllCustomers()));
    }

    @Override
    public ResponseEntity<Card> getCardsByCustomerId(String id) {
        return service.getCardByCustomerID(id).map(cardAssembler::toModel).map(ResponseEntity::ok)
                .orElse(notFound().build());
    }

    @Override
    public ResponseEntity<User> getCustomerById(String id) {
        return service.getCustomerById(id).map(assembler::toModel).map(ResponseEntity::ok)
                .orElse(notFound().build());
    }
}
