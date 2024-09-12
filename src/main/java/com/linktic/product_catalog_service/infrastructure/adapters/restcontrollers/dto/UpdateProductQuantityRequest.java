package com.linktic.product_catalog_service.infrastructure.adapters.restcontrollers.dto;

import com.linktic.product_catalog_service.domain.model.Product;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UpdateProductQuantityRequest {

    private Long id;
    private Integer quantity;

    public Product convertToDomain() {
        return Product.builder()
                .id(id)
                .quantity(quantity)
                .build();
    }

}
