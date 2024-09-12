package com.linktic.product_catalog_service.infrastructure.db.jpa;

import com.linktic.product_catalog_service.infrastructure.db.model.CategoryEntity;
import com.linktic.product_catalog_service.infrastructure.db.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ProductJPARepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByName(String name);

    List<ProductEntity> findByCategory(CategoryEntity category);

}
