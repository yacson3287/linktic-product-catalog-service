package com.linktic.product_catalog_service.application.usescases;

import com.linktic.product_catalog_service.domain.exceptions.BadRequestExceptionService;
import com.linktic.product_catalog_service.domain.exceptions.ExceptionDetail;
import com.linktic.product_catalog_service.domain.model.Category;
import com.linktic.product_catalog_service.domain.model.Product;
import com.linktic.product_catalog_service.domain.ports.in.FindProductUseCase;
import com.linktic.product_catalog_service.domain.ports.out.CategoryRepository;
import com.linktic.product_catalog_service.domain.ports.out.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindProductUseCaseImpl implements FindProductUseCase {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product execute(Long id) {
        var product = productRepository.findById(id);

        if (product == null) {
            var exception = new ExceptionDetail("The resource not exist");
            exception.addDetail("id", "No exist a resource with this id");
            throw new BadRequestExceptionService(exception);
        }

        return product;
    }

    @Override
    public List<Product> execute(Category category) {
        validateCategory(category);
        return productRepository.findByCategory(category);
    }

    @Override
    public List<Product> execute() {
        return productRepository.findAll();
    }

    private void validateCategory(Category category) {
        category = categoryRepository.findById(category.getId());
        if (category == null) {
            var exception = new ExceptionDetail("The resource not exist");
            exception.addDetail("category", "No exist a resource with this id");
            throw new BadRequestExceptionService(exception);
        }


    }
}
