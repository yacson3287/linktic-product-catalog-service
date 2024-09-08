package com.linktic.product_catalog_service.application.usescases;

import com.linktic.product_catalog_service.domain.exceptions.BadRequestExceptionService;
import com.linktic.product_catalog_service.domain.exceptions.ExceptionDetail;
import com.linktic.product_catalog_service.domain.model.Category;
import com.linktic.product_catalog_service.domain.ports.in.UpdateCategoryUseCase;
import com.linktic.product_catalog_service.domain.ports.out.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCategoryUseCaseImpl implements UpdateCategoryUseCase {

    private final CategoryRepository categoryRepository;

    @Override
    public Category execute(Category category) {

        var currentCategory = findCurrentCategory(category.getId());
        validateName(currentCategory, category);

        currentCategory.setName(category.getName());
        currentCategory.setDescription(category.getDescription());

        return categoryRepository.save(currentCategory);
    }

    private Category findCurrentCategory(Long id) {
        var category = categoryRepository.findById(id);
        if (category == null) {
            var exception = new ExceptionDetail("The resource not exist");
            exception.addDetail("id", "No exist a resource with this id");
            throw new BadRequestExceptionService(exception);
        }
        return category;
    }

    private void validateName(Category currentCategory, Category newCategory) {

        if (!currentCategory.getName().equals(newCategory.getName())) {

            var category = categoryRepository.findByName(newCategory.getName());

            if (category != null) {
                var exception = new ExceptionDetail("Operation could not be executed");
                exception.addDetail("name", "this name already used for other registry");
                throw new BadRequestExceptionService(exception);
            }
        }
    }

}
