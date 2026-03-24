/**
 * CategoryService Implementation.
 */
package com.akps.demo.services.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.akps.demo.mapper.CategoryMapper;
import com.akps.demo.repositories.CategoryRepository;
import com.akps.demo.requests.CreateCategoryRequest;
import com.akps.demo.responses.CategoryResponse;
import com.akps.demo.services.CategoryService;

/**
 * Implemented by AKPS at March 2026.
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    // declare and auto create category repository
    @Autowired
    private CategoryRepository categoryRepository;

    // category save method
    @Override
    public CategoryResponse save(CreateCategoryRequest request) {
        return CategoryMapper.toResponse(categoryRepository.save(CategoryMapper.toEntity(request)));
	}
}
