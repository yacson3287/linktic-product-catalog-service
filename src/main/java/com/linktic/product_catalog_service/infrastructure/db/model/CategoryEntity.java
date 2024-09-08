package com.linktic.product_catalog_service.infrastructure.db.model;

import com.linktic.product_catalog_service.domain.model.Category;
import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createAt;

    @PrePersist
    private void prePersist() {
        this.createAt = LocalDateTime.now();
    }

    public static CategoryEntity convertFromDomain(Category category) {
        return new ModelMapper().map(category, CategoryEntity.class);
    }

    public Category convertToDomain() {
        return new ModelMapper().map(this, Category.class);
    }
}
