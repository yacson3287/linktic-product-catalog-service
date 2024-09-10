package com.linktic.product_catalog_service.domain.ports.in;

import com.linktic.product_catalog_service.domain.model.Product;

public interface SubTractProductUseCase {

    Product execute(Product product);



}
