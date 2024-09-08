package com.linktic.product_catalog_service.domain.ports.in;

import com.linktic.product_catalog_service.domain.model.Category;

public interface UpdateCategoryUseCase {
    Category execute(Category category);
}
