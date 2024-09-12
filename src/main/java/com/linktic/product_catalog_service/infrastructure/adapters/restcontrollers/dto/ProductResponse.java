package com.linktic.product_catalog_service.infrastructure.adapters.restcontrollers.dto;

import com.linktic.product_catalog_service.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductResponse {

    private Long id;
    private String name;
    private int quantity;
    private Double price;
    private Long categoryId;

    public static ProductResponse convertFromDomain(Product product) {
        return new ModelMapper().map(product, ProductResponse.class);
    }
}
