package com.sourya.ecommerceAPI.hateoas;

import com.sourya.ecommerceAPI.controllers.PaymentController;
import com.sourya.ecommerceAPI.entity.PaymentEntity;
import com.sourya.ecommerceAPI.model.Payment;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class PaymentRepresentationModelAssembler extends RepresentationModelAssemblerSupport<PaymentEntity, Payment> {
    /**
     * Creates a new {@link RepresentationModelAssemblerSupport} using the given controller class and resource type.
     *
     * @param controllerClass must not be {@literal null}.
     * @param resourceType    must not be {@literal null}.
     */
    public PaymentRepresentationModelAssembler() {
        super(PaymentController.class. Payment.class);
    }

    @Override
    public Payment toModel(PaymentEntity entity) {
        Payment resource = createModelWithId(entity.getId(), entity);
        BeanUtils.copyProperties(entity, resource);
        resource.setId(entity.getId().toString());
        resource.add(linkTo(methodOn(PaymentController.class).getOrdersPaymentAuthorization(entity.getId().toString()))
                .withSelfRel());
        return resource;
    }

    public List<Payment> toListModel(Iterable<PaymentEntity> entities){
        if(Objects.isNull(entities)) {
            return Collections.emptyList();
        }
        return StreamSupport.stream(entities.spliterator(), false).map(e -> toModel(e))
                .collect(Collectors.toList());
    }
}
