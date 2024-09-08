package com.linktic.product_catalog_service.domain.model;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product {
    private Long id;
    private String name;
    private int quantity;
    private Double price;
    private LocalDateTime createAt;
    private Category category;
}
