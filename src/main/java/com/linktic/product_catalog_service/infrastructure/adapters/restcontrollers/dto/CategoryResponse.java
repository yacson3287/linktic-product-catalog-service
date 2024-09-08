package com.linktic.product_catalog_service.infrastructure.adapters.restcontrollers.dto;

import com.linktic.product_catalog_service.domain.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CategoryResponse {
    private Long id;
    private String name;
    private String description;

    public static CategoryResponse convertFromDomain(Category category) {
        return new ModelMapper().map(category, CategoryResponse.class);
    }
}
