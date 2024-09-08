package com.linktic.product_catalog_service.application.usescases;

import com.linktic.product_catalog_service.domain.exceptions.BadRequestExceptionService;
import com.linktic.product_catalog_service.domain.exceptions.ExceptionDetail;
import com.linktic.product_catalog_service.domain.model.Product;
import com.linktic.product_catalog_service.domain.ports.in.CreateProductUseCase;
import com.linktic.product_catalog_service.domain.ports.out.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProductUseCaseImpl implements CreateProductUseCase {

    private final ProductRepository productRepository;

    @Override
    public Product execute(Product product) {
        validateName(product.getName());
        return productRepository.save(product);
    }

    private void validateName(String name) {
        var product = productRepository.findByName(name);

        if (product != null) {
            var exception = new ExceptionDetail("Operation could not be executed");
            exception.addDetail("name", "this name already used for other registry");
            throw new BadRequestExceptionService(exception);
        }
    }
}
