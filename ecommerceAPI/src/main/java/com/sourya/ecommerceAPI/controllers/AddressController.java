package com.sourya.ecommerceAPI.controllers;

import com.sourya.ecommerceAPI.api.AddressApi;
import com.sourya.ecommerceAPI.hateoas.AddressRepresentationModelAssembler;
import com.sourya.ecommerceAPI.model.AddAddressReq;
import com.sourya.ecommerceAPI.model.Address;
import com.sourya.ecommerceAPI.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

public class AddressController implements AddressApi {

    private AddressService service;
    private final AddressRepresentationModelAssembler assembler;

    public AddressController(AddressService addressService, AddressRepresentationModelAssembler assembler) {
        this.service = addressService;
        this.assembler = assembler;
    }



    @Override
    public ResponseEntity<Address> createAddress(@Valid AddAddressReq addAddressReq) {
        return status(HttpStatus.CREATED).body(service.createAddress(addAddressReq)
                .map(assembler::toModel).get());
    }

    @Override
    public ResponseEntity<Void> deleteAddressesById(String id) {
        service.deleteAddressesById(id);
        return accepted().build();
    }

    @Override
    public ResponseEntity<Address> getAddressesById(String id) {
        return service.getAddressesById(id).map(assembler::toModel)
                .map(ResponseEntity::ok).orElse(notFound().build());
    }

    @Override
    public ResponseEntity<List<Address>> getAllAddresses() {
        return ok(assembler.toListModel(service.getAllAddresses()));
    }
}
