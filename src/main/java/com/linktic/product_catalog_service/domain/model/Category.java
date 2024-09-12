package com.linktic.product_catalog_service.domain.model;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Category {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createAt;
}
