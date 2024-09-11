package com.linktic.product_catalog_service.infrastructure.adapters.restcontrollers.dto;

import com.linktic.product_catalog_service.domain.model.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryUpdateRequest {

    @NotBlank
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;

    public Category convertToDomain() {
        return new ModelMapper().map(this, Category.class);
    }
}
