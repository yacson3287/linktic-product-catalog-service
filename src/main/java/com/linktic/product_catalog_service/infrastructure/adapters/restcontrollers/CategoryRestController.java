package com.linktic.product_catalog_service.infrastructure.adapters.restcontrollers;

import com.linktic.product_catalog_service.domain.ports.in.CreateCategoryUseCase;
import com.linktic.product_catalog_service.domain.ports.in.FindCategoryUseCase;
import com.linktic.product_catalog_service.domain.ports.in.UpdateCategoryUseCase;
import com.linktic.product_catalog_service.infrastructure.adapters.restcontrollers.dto.CategoryCreateRequest;
import com.linktic.product_catalog_service.infrastructure.adapters.restcontrollers.dto.CategoryResponse;
import com.linktic.product_catalog_service.infrastructure.adapters.restcontrollers.dto.CategoryUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryRestController {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final FindCategoryUseCase findCategoryUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoryResponse create(@RequestBody @Valid CategoryCreateRequest request) {
        var category = createCategoryUseCase.execute(request.convertToDomain());
        return CategoryResponse.convertFromDomain(category);
    }

    @GetMapping("{id}")
    public CategoryResponse findById(@PathVariable Long id) {
        var category = findCategoryUseCase.execute(id);
        return CategoryResponse.convertFromDomain(category);
    }

    @GetMapping
    public List<CategoryResponse> findById() {
        var categories = findCategoryUseCase.execute();
        return categories.stream()
                .map(CategoryResponse::convertFromDomain)
                .toList();
    }

    @PutMapping
    public CategoryResponse update(@RequestBody CategoryUpdateRequest request) {
        var category = updateCategoryUseCase.execute(request.convertToDomain());
        return CategoryResponse.convertFromDomain(category);
    }


}
