package com.linktic.product_catalog_service.infrastructure.adapters.repositories;

import com.linktic.product_catalog_service.domain.model.Category;
import com.linktic.product_catalog_service.domain.ports.out.CategoryRepository;
import com.linktic.product_catalog_service.infrastructure.db.jpa.CategoryJPARepository;
import com.linktic.product_catalog_service.infrastructure.db.model.CategoryEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {

    private final CategoryJPARepository categoryJPARepository;

    @Override
    public Category findById(Long id) {
        var entity = categoryJPARepository.findById(id);
        return entity.map(CategoryEntity::convertToDomain).orElse(null);
    }

    @Override
    public Category findByName(String name) {
        var entity = categoryJPARepository.findByName(name);
        return entity.map(CategoryEntity::convertToDomain).orElse(null);
    }

    @Override
    public List<Category> findAll() {
        var entities = categoryJPARepository.findAll();
        return entities.stream()
                .map(CategoryEntity::convertToDomain)
                .toList();
    }

    @Transactional
    @Override
    public Category save(Category category) {
        var entity = categoryJPARepository.save(CategoryEntity.convertFromDomain(category));
        return entity.convertToDomain();
    }

    @Transactional
    @Override
    public void delete(Category category) {
        categoryJPARepository.delete(CategoryEntity.convertFromDomain(category));
    }
}
