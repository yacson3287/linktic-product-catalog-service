package com.linktic.product_catalog_service.application.usescases;

import com.linktic.product_catalog_service.domain.exceptions.BadRequestExceptionService;
import com.linktic.product_catalog_service.domain.exceptions.ExceptionDetail;
import com.linktic.product_catalog_service.domain.model.Category;
import com.linktic.product_catalog_service.domain.ports.in.CreateCategoryUseCase;
import com.linktic.product_catalog_service.domain.ports.out.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCategoryUseCaseImpl implements CreateCategoryUseCase {

    private final CategoryRepository categoryRepository;

    @Override
    public Category execute(Category category) {
        validateName(category.getName());
        return categoryRepository.save(category);
    }

    private void validateName(String name) {
        var category = categoryRepository.findByName(name);
        if (category != null) {
            var exception = new ExceptionDetail("Operation could not be executed");
            exception.addDetail("name", "this name already used for other registry");
            throw new BadRequestExceptionService(exception);
        }
    }


}
