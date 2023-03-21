package com.sourya.ecommerceAPI.hateoas;

import com.sourya.ecommerceAPI.entity.OrderEntity;
import com.sourya.ecommerceAPI.model.Order;
import com.sourya.ecommerceAPI.service.ItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class OrderRepresentationModelAssembler extends RepresentationModelAssemblerSupport<OrderEntity, Order> {

    /**
     * Creates a new {@link RepresentationModelAssemblerSupport} using the given controller class and resource type.
     *
     * @param controllerClass must not be {@literal null}.
     * @param resourceType    must not be {@literal null}.
     */

    private UserRepresentationModelAssembler uAssembler;
    private AddressRepresentationModelAssembler aAssembler;
    private CardRepresentationModelAssembler cAssembler;
    private ShipmentRepresentationModelAssembler sAssembler;
    private ItemService itemService;\

    public OrderRepresentationModelAssembler(Class<?> controllerClass, Class<Order> resourceType, UserRepresentationModelAssembler uAssembler, AddressRepresentationModelAssembler aAssembler, CardRepresentationModelAssembler cAssembler, ShipmentRepresentationModelAssembler sAssembler, ItemService itemService) {
        super(OrderController.class, Order.class);
        this.uAssembler = uAssembler;
        this.aAssembler = aAssembler;
        this.cAssembler = cAssembler;
        this.sAssembler = sAssembler;
        this.itemService = itemService;
    }

    @Override
    public Order toModel(OrderEntity entity) {
        System.out.println("\n\nentity = " + entity);
        Order resource = createModelWithId(entity.getId(), entity);
        BeanUtils.copyProperties(entity, resource);
        resource.id(entity.getId().toString())
                .customer(uAssembler.toModel(entity.getUserEntity()))
                .address(aAssembler.toModel(entity.getAddressEntity()))
                .card(cAssembler.toModel(entity.getCardEntity()))
                .items(itemService.toModelList(entity.getItems()))
                .date(entity.getOrderDate().toInstant().atOffset(ZoneOffset.UTC));

        System.out.println("resource = " + resource);

        resource.add(linkTo(methodOn(OrderController.class).getByOrderId(entity.getId().toString()))
                .withSelfRel());
        return resource;

    }

    public List<Order> toListModel(Iterable<OrderEntity> entities) {
        if(Objects.isNull(entities)) {
            return Collections.emptyList();
        }
        return StreamSupport.stream(entities.spliterator(), false).map(e -> toModel(e))
                .collect(Collectors.toList());
    }
}
