package com.linktic.product_catalog_service.infrastructure.adapters.restcontrollers;

import com.linktic.product_catalog_service.domain.ports.in.CreateProductUseCase;
import com.linktic.product_catalog_service.infrastructure.adapters.restcontrollers.dto.ProductCreateRequest;
import com.linktic.product_catalog_service.infrastructure.adapters.restcontrollers.dto.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductRestController {

    private final CreateProductUseCase createProductUseCase;


    @PostMapping
    public ProductResponse create(@RequestBody @Valid ProductCreateRequest request) {
        var product = createProductUseCase.execute(request.convertToDomain());
        return ProductResponse.convertFromDomain(product);
    }
}
