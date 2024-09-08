package com.linktic.product_catalog_service.infrastructure.adapters.restcontrollers.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdateProductQuantityRequest {

    @NotNull
    private Long productId;
    @NotNull
    @Positive
    private Integer quantity;

}
