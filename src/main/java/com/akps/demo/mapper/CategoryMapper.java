package com.akps.demo.mapper;

import org.springframework.stereotype.Component;
import com.akps.demo.models.*;
import com.akps.demo.responses.*;
import com.akps.demo.requests.*;


@Component
public class CategoryMapper
{

    // Convert DTO/Response to Entity
    public static Category toEntity(CreateCategoryRequest request)
    {
        if (request == null) return null;

        return Category.builder()
                       .name(request.getName())
                       .build();
    }

    // Convert Entity to DTO/Response
    public static CategoryResponse toResponse(Category category)
    {
        if (category == null) return null;

        return CategoryResponse.builder()
                               .id(category.getId())
                               .name(category.getName())
                               .build();
    }
}