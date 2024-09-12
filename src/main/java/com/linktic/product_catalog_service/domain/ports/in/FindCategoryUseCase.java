package com.linktic.product_catalog_service.domain.ports.in;

import com.linktic.product_catalog_service.domain.model.Category;

import java.util.List;

public interface FindCategoryUseCase {

    Category execute(Long id);

    List<Category> execute();

}
