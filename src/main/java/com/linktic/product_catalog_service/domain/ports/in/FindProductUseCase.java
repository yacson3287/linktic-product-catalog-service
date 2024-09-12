package com.linktic.product_catalog_service.domain.ports.in;

import com.linktic.product_catalog_service.domain.model.Category;
import com.linktic.product_catalog_service.domain.model.Product;

import java.util.List;

public interface FindProductUseCase {

    Product execute(Long id);

    List<Product> execute(Category category);

    List<Product> execute();

    List<Product> execute(List<Long> ids);

}
