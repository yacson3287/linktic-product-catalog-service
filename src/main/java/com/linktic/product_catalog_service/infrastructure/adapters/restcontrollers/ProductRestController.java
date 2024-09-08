package com.linktic.product_catalog_service.infrastructure.adapters.restcontrollers;

import com.linktic.product_catalog_service.domain.model.Category;
import com.linktic.product_catalog_service.domain.ports.in.CreateProductUseCase;
import com.linktic.product_catalog_service.domain.ports.in.FindProductUseCase;
import com.linktic.product_catalog_service.infrastructure.adapters.restcontrollers.dto.ProductCreateRequest;
import com.linktic.product_catalog_service.infrastructure.adapters.restcontrollers.dto.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductRestController {

    private final CreateProductUseCase createProductUseCase;
    private final FindProductUseCase findProductUseCase;


    @PostMapping
    public ProductResponse create(@RequestBody @Valid ProductCreateRequest request) {
        var product = createProductUseCase.execute(request.convertToDomain());
        return ProductResponse.convertFromDomain(product);
    }

    @GetMapping("{id}")
    public ProductResponse findById(@PathVariable Long id) {
        var product = findProductUseCase.execute(id);
        return ProductResponse.convertFromDomain(product);
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductResponse> findByCategory(@PathVariable Long categoryId) {

        var products = findProductUseCase.execute(Category.builder()
                .id(categoryId)
                .build()
        );

        return products.stream()
                .map(ProductResponse::convertFromDomain)
                .toList();
    }

    @GetMapping
    public List<ProductResponse> findAll() {
        var products = findProductUseCase.execute();
        return products.stream()
                .map(ProductResponse::convertFromDomain)
                .toList();
    }


}
