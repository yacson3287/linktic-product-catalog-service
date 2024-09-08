package com.linktic.product_catalog_service.application.usescases;

import com.linktic.product_catalog_service.domain.exceptions.BadRequestExceptionService;
import com.linktic.product_catalog_service.domain.exceptions.ExceptionDetail;
import com.linktic.product_catalog_service.domain.model.Product;
import com.linktic.product_catalog_service.domain.ports.in.AddProductsUseCase;
import com.linktic.product_catalog_service.domain.ports.out.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddProductsUseCaseImpl implements AddProductsUseCase {

    private final ProductRepository productRepository;

    @Override
    public Product execute(Long productId, int quantity) {

        var product = findProduct(productId);

        var newQuantity = product.getQuantity() + quantity;

        product.setQuantity(newQuantity);
        return productRepository.save(product);
    }

    private Product findProduct(Long id) {
        var product = productRepository.findById(id);
        if (product == null) {
            var exception = new ExceptionDetail("The resource not exist");
            exception.addDetail("category", "No exist a resource with this id");
            throw new BadRequestExceptionService(exception);
        }
        return product;
    }
}
