package com.codegym.service.category;

import com.codegym.model.entity.dish.category.Category;
import com.codegym.model.entity.dish.category.CategoryDTO;
import com.codegym.service.IGeneralService;

public interface ICategoryService extends IGeneralService<Category> {
    Iterable<CategoryDTO> getAllCategoryDTO();
}