package com.linktic.product_catalog_service.application.usescases;

import com.linktic.product_catalog_service.domain.exceptions.BadRequestExceptionService;
import com.linktic.product_catalog_service.domain.exceptions.ExceptionDetail;
import com.linktic.product_catalog_service.domain.model.Category;
import com.linktic.product_catalog_service.domain.ports.in.FindCategoryUseCase;
import com.linktic.product_catalog_service.domain.ports.out.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindCategoryUseCaseImpl implements FindCategoryUseCase {

    private final CategoryRepository categoryRepository;

    @Override
    public Category execute(Long id) {
        var category = categoryRepository.findById(id);

        if (category == null) {
            var exception = new ExceptionDetail("The resource not exist");
            exception.addDetail("id", "No exist a resource with this id");
            throw new BadRequestExceptionService(exception);
        }

        return category;
    }

    @Override
    public List<Category> execute() {
        return categoryRepository.findAll();
    }
}
