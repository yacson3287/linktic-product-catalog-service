package com.linktic.product_catalog_service.infrastructure.db.jpa;

import com.linktic.product_catalog_service.infrastructure.db.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryJPARepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByName(String name);

}
