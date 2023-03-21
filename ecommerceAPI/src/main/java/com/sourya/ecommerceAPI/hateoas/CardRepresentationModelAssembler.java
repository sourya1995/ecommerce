package com.sourya.ecommerceAPI.hateoas;

import com.sourya.ecommerceAPI.entity.CardEntity;
import com.sourya.ecommerceAPI.model.Card;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class CardRepresentationModelAssembler extends RepresentationModelAssemblerSupport<CardEntity, Card> {

    /**
     * Creates a new {@link RepresentationModelAssemblerSupport} using the given controller class and resource type.
     *
     * @param controllerClass must not be {@literal null}.
     * @param resourceType    must not be {@literal null}.
     */
    public CardRepresentationModelAssembler() {
        super(CardController.class, Card.class);
    }

    @Override
    public Card toModel(CardEntity entity) {
        String uid = Objects.nonNull(entity.getUser()) ? entity.getUser().getId().toString() : null;
        Card resource = createModelWithId(entity.getId(), entity);
        BeanUtils.copyProperties(entity, resource);
        resource.id(entity.getId().toString()).cardNumber(entity.getNumber())
                .cvv(entity.getCvv()).expires(entity.getExpires()).userId(uid);
        resource.add(linkTo(methodOn(CardController.class).getCardById(entity.getId().toString())).withSelfRel());
        return resource;

    }

    public List<Card> toListModel(Iterable<CardEntity> entities){
        if(Objects.isNull(entities)) return Collections.emptyList();
        return StreamSupport.stream(entities.spliterator(), false).map(e -> toModel(e)).collect(Collectors.toList());
    }
}
