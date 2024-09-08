package com.linktic.product_catalog_service.infrastructure.adapters.repositories;

import com.linktic.product_catalog_service.domain.model.Category;
import com.linktic.product_catalog_service.domain.model.Product;
import com.linktic.product_catalog_service.domain.ports.out.ProductRepository;
import com.linktic.product_catalog_service.infrastructure.db.jpa.ProductJPARepository;
import com.linktic.product_catalog_service.infrastructure.db.model.CategoryEntity;
import com.linktic.product_catalog_service.infrastructure.db.model.ProductEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJPARepository productJPARepository;

    @Transactional
    @Override
    public Product save(Product product) {
        var entity = productJPARepository.save(ProductEntity.convertFromDomain(product));
        return entity.convertToDomain();
    }

    @Override
    public Product findById(Long id) {
        var entity = productJPARepository.findById(id);
        return entity.map(ProductEntity::convertToDomain).orElse(null);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        var entities = productJPARepository.findByCategory(CategoryEntity.convertFromDomain(category));
        return entities.stream()
                .map(ProductEntity::convertToDomain)
                .toList();
    }

    @Override
    public List<Product> findAll() {
        var entities = productJPARepository.findAll();
        return entities.stream()
                .map(ProductEntity::convertToDomain)
                .toList();
    }

    @Override
    public Product findByName(String name) {
        var entity = productJPARepository.findByName(name);
        return entity.map(ProductEntity::convertToDomain).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Product product) {
        productJPARepository.delete(ProductEntity.convertFromDomain(product));
    }
}
