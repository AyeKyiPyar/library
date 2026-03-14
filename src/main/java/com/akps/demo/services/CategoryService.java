package com.akps.demo.services;

import com.akps.demo.requests.*;
import com.akps.demo.responses.*;

public interface CategoryService
{
	CategoryResponse save(CreateCategoryRequest request);
}
