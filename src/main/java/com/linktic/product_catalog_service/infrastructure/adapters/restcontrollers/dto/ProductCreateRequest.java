package com.linktic.product_catalog_service.infrastructure.adapters.restcontrollers.dto;

import com.linktic.product_catalog_service.domain.model.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
@Getter
public class ProductCreateRequest {

    @NotBlank
    private String name;

    @NotNull
    private Integer quantity;

    @NotNull
    private Double price;

    @NotNull
    private Long categoryId;

    public Product convertToDomain() {
        return new ModelMapper().map(this, Product.class);
    }
}
