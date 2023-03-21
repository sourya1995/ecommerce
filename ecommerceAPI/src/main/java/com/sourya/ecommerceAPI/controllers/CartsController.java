package com.sourya.ecommerceAPI.controllers;

import com.sourya.ecommerceAPI.api.CartApi;
import com.sourya.ecommerceAPI.hateoas.CartRepresentationModelAssembler;
import com.sourya.ecommerceAPI.model.Cart;
import com.sourya.ecommerceAPI.model.Item;
import com.sourya.ecommerceAPI.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.accepted;
import static org.springframework.http.ResponseEntity.ok;

public class CartsController implements CartApi {
    private static final Logger log = LoggerFactory.getLogger(CartsController.class);
    private CartService cartService;
    private final CartRepresentationModelAssembler assembler;

    public CartsController(CartService cartService, CartRepresentationModelAssembler assembler) {
        this.cartService = cartService;
        this.assembler = assembler;
    }




    @Override
    public ResponseEntity<List<Item>> addCartItemsByCustomerId(String customerId, @Valid Item item) {
        log.info("Request for Customer ID: {}\nItem: {}", customerId, item);
        return ResponseEntity.ok(cartService.addCartItemsByCustomerId(customerId, item));
    }

    @Override
    public ResponseEntity<List<Item>> addOrReplaceItemsByCustomerId(String customerId, @Valid Item item) {
        return ResponseEntity.ok(cartService.addOrReplaceItemsByCustomerId(customerId, item));
    }

    @Override
    public ResponseEntity<Void> deleteCart(String customerId) {
        cartService.deleteCart(customerId);
        return accepted().build();
    }

    @Override
    public ResponseEntity<Void> deleteItemFromCart(String customerId, String itemId) {
        cartService.deleteItemFromCart(customerId, itemId);
        return accepted().build();
    }

    @Override
    public ResponseEntity<List<Cart>> getCartByCustomerId(String customerId) {
        return ok(assembler.toModel(cartService.getCartByCustomerId(customerId)));
    }

    @Override
    public ResponseEntity<List<Item>> getCartItemsByCustomerId(String customerId) {
        return ok(cartService.getCartItemsByCustomerId(customerId));
    }

    @Override
    public ResponseEntity<List<Item>> getCartItemsByItemId(String customerId, String itemId) {
        return ok(cartService.getCartItemsByCustomerId(customerId, itemId));
    }
}