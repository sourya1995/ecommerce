package com.sourya.ecommerceAPI.hateoas;

import com.sourya.ecommerceAPI.entity.ShipmentEntity;
import com.sourya.ecommerceAPI.model.Shipment;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ShipmentRepresentationModelAssembler extends RepresentationModelAssemblerSupport<ShipmentEntity, Shipment> {
    /**
     * Creates a new {@link RepresentationModelAssemblerSupport} using the given controller class and resource type.
     *
     * @param controllerClass must not be {@literal null}.
     * @param resourceType    must not be {@literal null}.
     */
    public ShipmentRepresentationModelAssembler() {
        super(ShipmentController.class, Shipment);
    }

    @Override
    public Shipment toModel(ShipmentEntity entity) {
        Shipment resource = createModelWithId(entity.getId(), entity);
        BeanUtils.copyProperties(entity, resource);
        resource.setId(entity.getId().toString());
        resource.add(linkTo(methodOn(ShipmentController.class).getShipmentByOrderId(entity.getId(),toString())).withRel("byOrderId"));
        return resource;
    }

    public List<Shipment> toListModel(Iterable<ShipmentEntity> entities) {
        if(Objects.isNull(entities)) return Collections.emptyList();
        return StreamSupport.stream(entities.spliterator(), false).map(e -> toModel(e)).collect(Collectors.toList());
    }
}
