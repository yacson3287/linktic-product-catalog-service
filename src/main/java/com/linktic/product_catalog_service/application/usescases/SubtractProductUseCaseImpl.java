package com.linktic.product_catalog_service.application.usescases;

import com.linktic.product_catalog_service.domain.exceptions.BadRequestExceptionService;
import com.linktic.product_catalog_service.domain.exceptions.ExceptionDetail;
import com.linktic.product_catalog_service.domain.model.Product;
import com.linktic.product_catalog_service.domain.ports.in.SubTractProductUseCase;
import com.linktic.product_catalog_service.domain.ports.out.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubtractProductUseCaseImpl implements SubTractProductUseCase {

    private final ProductRepository productRepository;

    @Override
    public Product execute(Long productId, int quantity) {
        var product = findProduct(productId);
        subtract(product, quantity);
        return productRepository.save(product);
    }

    private void subtract(Product product, int quantity) {
        if (quantity > product.getQuantity()) {
            var exception = new ExceptionDetail("It isn't possible complete this operation");
            exception.addDetail("quantity", "it has not enough quantity the this product");
            throw new BadRequestExceptionService(exception);
        }
        var newQuantity = product.getQuantity() - quantity;
        product.setQuantity(newQuantity);
    }

    private Product findProduct(Long id) {
        var product = productRepository.findById(id);
        if (product == null) {
            var exception = new ExceptionDetail("The resource not exist");
            exception.addDetail("id", "No exist a resource with this id");
            throw new BadRequestExceptionService(exception);
        }
        return product;
    }
}
