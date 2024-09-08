package com.linktic.product_catalog_service.domain.ports.out;

import com.linktic.product_catalog_service.domain.model.Category;

import java.util.List;

public interface CategoryRepository {

    Category findById(Long id);

    Category findByName(String name);

    List<Category> findAll();

    Category save(Category category);

    void delete(Category category);
}
