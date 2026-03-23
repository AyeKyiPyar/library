package com.akps.demo.services.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.akps.demo.mapper.CategoryMapper;

import com.akps.demo.repositories.CategoryRepository;
import com.akps.demo.requests.CreateCategoryRequest;
import com.akps.demo.responses.CategoryResponse;
import com.akps.demo.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService
{
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public CategoryResponse save(CreateCategoryRequest request)
	{
		
		return CategoryMapper.toResponse(categoryRepository.save(CategoryMapper.toEntity(request)));
	}

}
