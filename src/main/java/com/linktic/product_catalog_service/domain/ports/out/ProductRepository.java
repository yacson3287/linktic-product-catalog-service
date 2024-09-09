package com.linktic.product_catalog_service.domain.ports.out;

import com.linktic.product_catalog_service.domain.model.Category;
import com.linktic.product_catalog_service.domain.model.Product;

import java.util.List;

public interface ProductRepository {

    Product save(Product product);

    Product findById(Long id);

    List<Product> findByCategory(Category category);

    List<Product> findAll();

    List<Product> findByIds(List<Long> ids);

    Product findByName(String name);

    void delete(Product product);
}
