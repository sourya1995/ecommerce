package com.sourya.ecommerceAPI.hateoas;

import com.sourya.ecommerceAPI.controllers.CartsController;
import com.sourya.ecommerceAPI.entity.CardEntity;
import com.sourya.ecommerceAPI.entity.CartEntity;
import com.sourya.ecommerceAPI.model.Cart;
import com.sourya.ecommerceAPI.service.ItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CartRepresentationModelAssembler extends RepresentationModelAssemblerSupport<CartEntity, Cart> {
    private ItemService itemService;

    public CartRepresentationModelAssembler() {
        super(CartsController.class, Cart.class);
        this.itemService = itemService;
    }

    public Cart toModel(CartEntity cartEntity){
        String uid = Objects.nonNull(cartEntity.getUser()) ? cartEntity.getUser().getId().toString() : null;
        String cid = Objects.nonNull(cartEntity.getId()) ? cartEntity.getId().toString() : null;
        Cart resource = new Cart();
        BeanUtils.copyProperties(cartEntity, resource);
        resource.id(cid).customerId(uid).items(itemService.toModelList(cartEntity.getItems()));
        resource.add(linkTo(methodOn(CartsController.class).getCartByCustomerId(uid)).withSelfRel());
        resource.add(linkTo(methodOn(CartsController.class).getCartItemsByCustomerId(uid.toString()))
                .withRel("cart-items"));
        return resource;

    }

    public List<Cart> toListModel(Iterable<CartEntity> entities) {
        if(Objects.isNull(entities)) {
            return Collections.emptyList();
        }
        return StreamSupport.stream(entities.spliterator(), false).map(e -> toModel(e))
                .collect(Collectors.toList());
    }
}
