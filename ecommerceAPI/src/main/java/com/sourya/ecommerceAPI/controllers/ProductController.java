package com.sourya.ecommerceAPI.controllers;

import com.sourya.ecommerceAPI.api.ProductApi;
import com.sourya.ecommerceAPI.hateoas.ProductRepresentationModelAssembler;
import com.sourya.ecommerceAPI.model.Product;
import com.sourya.ecommerceAPI.service.ProductService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.List;

public class ProductController implements ProductApi {
    private ProductService service;
    private final ProductRepresentationModelAssembler assembler;


    public ProductController(ProductService service, ProductRepresentationModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @Override
    public ResponseEntity<Product> getProduct(String id) {
        return service.getProduct(id).map(assembler::toModel).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<Product>> queryProducts(@Valid String tag, @Valid String name, @Valid Integer page, @Valid Integer size) {
        return ResponseEntity.ok(assembler.toListModel(service.getAllProducts()));
    }
}
