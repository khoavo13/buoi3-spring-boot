package com.example.buoi1.services;

import com.example.buoi1.dtos.CategoryDTO;
import com.example.buoi1.models.Category;

import java.util.List;

public interface ICategoryService {
    Category getCategoryById(Long id);
    List<Category> getAllCategories();
    Category saveCategory(CategoryDTO category);
    Category updateCategory(Long id, CategoryDTO category);
    void deleteCategory(Long id);
}
