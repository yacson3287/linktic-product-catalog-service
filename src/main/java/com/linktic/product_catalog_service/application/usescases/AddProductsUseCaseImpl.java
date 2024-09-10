package com.linktic.product_catalog_service.application.usescases;

import com.linktic.product_catalog_service.domain.model.Product;
import com.linktic.product_catalog_service.domain.ports.in.AddProductsUseCase;
import com.linktic.product_catalog_service.domain.ports.out.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AddProductsUseCaseImpl implements AddProductsUseCase {

    private final ProductRepository productRepository;

    @Override
    public List<Product> execute(List<Product> products) {
        var currentProducts = productRepository.findByIds(products.stream().map(Product::getId).toList());
        currentProducts.forEach(current -> addQuantity(current, products));
        return productRepository.save(currentProducts);
    }

    private void addQuantity(Product current, List<Product> products) {
        var product = products.stream().filter(p -> Objects.equals(p.getId(), current.getId())).findFirst();
        if (product.isPresent()) {
            var newQuantity = current.getQuantity() + product.get().getQuantity();
            current.setQuantity(newQuantity);
        }
    }

}
