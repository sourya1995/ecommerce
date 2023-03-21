package com.sourya.ecommerceAPI.hateoas;

import com.sourya.ecommerceAPI.controllers.AddressController;
import com.sourya.ecommerceAPI.entity.AddressEntity;
import com.sourya.ecommerceAPI.model.Address;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class AddressRepresentationModelAssembler extends RepresentationModelAssemblerSupport<AddressEntity, Address> {
    /**
     * Creates a new {@link RepresentationModelAssemblerSupport} using the given controller class and resource type.
     *
     * @param controllerClass must not be {@literal null}.
     * @param resourceType    must not be {@literal null}.
     */
    public AddressRepresentationModelAssembler() {
        super(AddressController.class, Address.class);
    }

    @Override
    public Address toModel(AddressEntity entity) {
        Address resource = createModelWithId(entity.getId(), entity);
        BeanUtils.copyProperties(entity, resource);
        resource.setId(entity.getId().toString());
        resource.add(
                linkTo(methodOn(AddressController.class).getAddressesById(entity.getId().toString()))
                        .withSelfRel()
        );
        return resource;
    }

    public List<Address> toListModel(Iterable<AddressEntity> entities) {
        if(Objects.isNull(entities)) {
            return Collections.emptyList();
        }
        return StreamSupport.stream(entities.spliterator(), false).map(e -> toModel(e))
                .collect(Collectors.toList());
    }
}
