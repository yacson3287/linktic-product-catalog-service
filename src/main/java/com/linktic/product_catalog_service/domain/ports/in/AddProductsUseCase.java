package com.linktic.product_catalog_service.domain.ports.in;

import com.linktic.product_catalog_service.domain.model.Product;

import java.util.List;

public interface AddProductsUseCase {
    List<Product> execute(List<Product> products);
}
