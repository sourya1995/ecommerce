package com.sourya.ecommerceAPI.hateoas;

import com.sourya.ecommerceAPI.entity.ProductEntity;
import com.sourya.ecommerceAPI.model.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ProductRepresentationModelAssembler extends RepresentationModelAssemblerSupport<ProductEntity, Product> {


    /**
     * Creates a new {@link RepresentationModelAssemblerSupport} using the given controller class and resource type.
     *
     * @param controllerClass must not be {@literal null}.
     * @param resourceType    must not be {@literal null}.
     */
    public ProductRepresentationModelAssembler() {
        super(ProductController.class, Product.class);
    }

    @Override
    public Product toModel(ProductEntity entity) {
        Product resource = createModelWithId(entity.getId(), entity);
        BeanUtils.copyProperties(entity, resource);
        resource.setId(entity.getId().toString());
        resource.setTag(
                entity.getTags().stream().map(t -> new Tag().id(t.getId().toString()).name(t.getName()))
                        .collect(Collectors.toList()));

                resource.add(linkTo(methodOn(ProductController.class).getProduct(entity.getId().toString()))
                        .withSelfRel());

                resource.add(linkTo(methodOn(ProductController.class).queryProducts(null, null, 1, 10))
                        .withRel("products"));
                return resource;

    }

    public List<Product> toListModel(Iterable<ProductEntity> entities){
        if(Objects.isNull(entities)){
            return Collections.emptyList();
        }
        return StreamSupport.stream(entities.spliterator(), false).map(p -> toModel(p))
                .collect(Collectors.toList());
    }
}
