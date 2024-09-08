package com.linktic.product_catalog_service.infrastructure.adapters.restcontrollers;

import com.linktic.product_catalog_service.domain.ports.in.CreateCategoryUseCase;
import com.linktic.product_catalog_service.infrastructure.adapters.restcontrollers.dto.CategoryCreateRequest;
import com.linktic.product_catalog_service.infrastructure.adapters.restcontrollers.dto.CategoryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryRestController {

    private final CreateCategoryUseCase createCategoryUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoryResponse create(@RequestBody @Valid CategoryCreateRequest request) {
        var category = createCategoryUseCase.execute(request.convertToDomain());
        return CategoryResponse.convertFromDomain(category);
    }


}
