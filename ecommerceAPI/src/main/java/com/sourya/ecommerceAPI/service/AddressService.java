package com.sourya.ecommerceAPI.service;

import com.sourya.ecommerceAPI.entity.AddressEntity;
import com.sourya.ecommerceAPI.model.AddAddressReq;

import java.util.Optional;

public interface AddressService {
    public Optional<AddressEntity> createAddress(AddAddressReq addAddressReq);
    public void deleteAddressesById(String id);
    public Optional<AddressEntity> getAddressesById(String id);
    public Iterable<AddressEntity> getAllAddresses();
}
