package com.linktic.product_catalog_service.infrastructure.adapters.restcontrollers;

import com.linktic.product_catalog_service.domain.model.Category;
import com.linktic.product_catalog_service.domain.ports.in.AddProductsUseCase;
import com.linktic.product_catalog_service.domain.ports.in.CreateProductUseCase;
import com.linktic.product_catalog_service.domain.ports.in.FindProductUseCase;
import com.linktic.product_catalog_service.domain.ports.in.SubTractProductUseCase;
import com.linktic.product_catalog_service.infrastructure.adapters.restcontrollers.dto.ProductCreateRequest;
import com.linktic.product_catalog_service.infrastructure.adapters.restcontrollers.dto.ProductResponse;
import com.linktic.product_catalog_service.infrastructure.adapters.restcontrollers.dto.UpdateProductQuantityRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
@Tag(name = "Product", description = "Catalog products management")
public class ProductRestController {

    private final CreateProductUseCase createProductUseCase;
    private final FindProductUseCase findProductUseCase;
    private final AddProductsUseCase addProductsUseCase;
    private final SubTractProductUseCase subTractProductUseCase;


    @Operation(summary = "Add a new product to catalog")
    @PostMapping
    public ProductResponse create(@RequestBody @Valid ProductCreateRequest request) {
        var product = createProductUseCase.execute(request.convertToDomain());
        return ProductResponse.convertFromDomain(product);
    }

    @Operation(summary = "find a product of catalog")
    @GetMapping("{id}")
    public ProductResponse findById(@PathVariable Long id) {
        var product = findProductUseCase.execute(id);
        return ProductResponse.convertFromDomain(product);
    }

    @Operation(summary = "Find products of a category")
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

    @Operation(summary = "Find all products of catalog")
    @GetMapping
    public List<ProductResponse> findAll() {
        var products = findProductUseCase.execute();
        return products.stream()
                .map(ProductResponse::convertFromDomain)
                .toList();
    }

    @Operation(summary = "Increase quantity of a product")
    @PutMapping("add-quantity")
    public List<ProductResponse> addProductQuantity(@RequestBody List<UpdateProductQuantityRequest> requests) {
        var products = addProductsUseCase.execute(requests.stream().map(UpdateProductQuantityRequest::convertToDomain).toList());
        return products.stream()
                .map(ProductResponse::convertFromDomain)
                .toList();
    }

    @Operation(summary = "Subtract quantity of a product")
    @PutMapping("subtract-quantity")
    public ProductResponse subtractProductQuantity(@RequestBody @Valid UpdateProductQuantityRequest request) {
        var product = subTractProductUseCase.execute(request.convertToDomain());
        return ProductResponse.convertFromDomain(product);
    }

    @Operation(summary = "Find product by a id list")
    @PostMapping("find-by-ids")
    public List<ProductResponse> findByIds(@RequestBody List<Long> ids) {
        var products = findProductUseCase.execute(ids);
        return products.stream()
                .map(ProductResponse::convertFromDomain)
                .toList();
    }


}
