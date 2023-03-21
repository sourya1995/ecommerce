package com.sourya.ecommerceAPI.hateoas;

import com.sourya.ecommerceAPI.entity.UserEntity;
import com.sourya.ecommerceAPI.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class UserRepresentationModelAssembler extends RepresentationModelAssemblerSupport<UserEntity, User> {
    /**
     * Creates a new {@link RepresentationModelAssemblerSupport} using the given controller class and resource type.
     *
     * @param controllerClass must not be {@literal null}.
     * @param resourceType    must not be {@literal null}.
     */
    public UserRepresentationModelAssembler() {
        super(UserController.class, User.class);
    }

    @Override
    public User toModel(UserEntity entity) {
        User resource = createModelWithId(entity.getId(), entity);
        BeanUtils.copyProperties(entity, resource);
        resource.setId(entity.getId().toString());
        resource.add(linkTo(methodOn(CustomerController.class).getCustomerById(entity.getId().toString()))
                .withSelfRel());
        resource.add(linkTo(methodOn(CustomerController.class).getAllCustomers()).withRel("customers"));
        resource.add(linkTo(methodOn(CustomerController.class).getAddressesByCustomerId(entity.getId().toString())).withRel("self_addresses"));
        return resource;
    }

    public List<User> toListModel(Iterable<UserEntity> entities) {
        if(Objects.isNull(entities)) {
            return Collections.emptyList();
        }
        return StreamSupport.stream(entities.spliterator(), false).map(e -> toModel(e))
                .collect(Collectors.toList());
    }
}
