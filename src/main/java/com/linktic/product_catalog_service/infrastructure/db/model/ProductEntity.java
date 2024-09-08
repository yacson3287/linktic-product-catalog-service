package com.linktic.product_catalog_service.infrastructure.db.model;

import com.linktic.product_catalog_service.domain.model.Product;
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
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int quantity;
    private Double price;
    private LocalDateTime createAt;

    @PrePersist
    private void prePersist() {
        this.createAt = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    public static ProductEntity convertFromDomain(Product product) {
        return new ModelMapper().map(product, ProductEntity.class);
    }

    public Product convertToDomain() {
        return new ModelMapper().map(this, Product.class);
    }
}
