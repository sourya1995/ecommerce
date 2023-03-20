package com.sourya.ecommerceAPI.service;

import com.sourya.ecommerceAPI.entity.AddressEntity;
import com.sourya.ecommerceAPI.model.AddAddressReq;
import com.sourya.ecommerceAPI.repository.AddressRepository;

import java.util.Optional;
import java.util.UUID;

public class AddressServiceImpl implements AddressService{

    private AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Optional<AddressEntity> createAddress(AddAddressReq addAddressReq) {
        return Optional.of(addressRepository.save(toEntity(addAddressReq)));
    }

    @Override
    public void deleteAddressesById(String id) {
        addressRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public Optional<AddressEntity> getAddressesById(String id) {
        return addressRepository.findById(UUID.fromString(id));
    }

    @Override
    public Iterable<AddressEntity> getAllAddresses() {
        return addressRepository.findAll();
    }

    private AddressEntity toEntity(AddAddressReq model){
        AddressEntity entity = new AddressEntity();
        return entity.setNumber(model.getNumber()).setResidency(model.getResidency())
                .setStreet(model.getStreet()).setCity(model.getCity()).setState(model.getState())
                .setCountry(model.getCountry()).setPincode(model.getPincode());
    }
}
