package com.linktic.product_catalog_service.infrastructure.adapters.restcontrollers;

import com.linktic.product_catalog_service.domain.ports.in.CreateCategoryUseCase;
import com.linktic.product_catalog_service.domain.ports.in.FindCategoryUseCase;
import com.linktic.product_catalog_service.domain.ports.in.UpdateCategoryUseCase;
import com.linktic.product_catalog_service.infrastructure.adapters.restcontrollers.dto.CategoryCreateRequest;
import com.linktic.product_catalog_service.infrastructure.adapters.restcontrollers.dto.CategoryResponse;
import com.linktic.product_catalog_service.infrastructure.adapters.restcontrollers.dto.CategoryUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
@Tag(name = "Category", description = "Catalog Categories management")
public class CategoryRestController {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final FindCategoryUseCase findCategoryUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;


    @Operation(summary = "Add a new category to catalog")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoryResponse create(@RequestBody @Valid CategoryCreateRequest request) {
        var category = createCategoryUseCase.execute(request.convertToDomain());
        return CategoryResponse.convertFromDomain(category);
    }

    @Operation(summary = "Find a category of catalog")
    @GetMapping("{id}")
    public CategoryResponse findById(@PathVariable Long id) {
        var category = findCategoryUseCase.execute(id);
        return CategoryResponse.convertFromDomain(category);
    }

    @Operation(summary = "Find all categories of catalog")
    @GetMapping
    public List<CategoryResponse> findAll() {
        var categories = findCategoryUseCase.execute();
        return categories.stream()
                .map(CategoryResponse::convertFromDomain)
                .toList();
    }

    @Operation(summary = "update a category of catalog")
    @PutMapping
    public CategoryResponse update(@RequestBody @Valid CategoryUpdateRequest request) {
        var category = updateCategoryUseCase.execute(request.convertToDomain());
        return CategoryResponse.convertFromDomain(category);
    }


}
