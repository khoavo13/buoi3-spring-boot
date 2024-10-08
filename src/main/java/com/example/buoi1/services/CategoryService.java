package com.example.buoi1.services;

import com.example.buoi1.dtos.CategoryDTO;
import com.example.buoi1.models.Category;
import com.example.buoi1.repositories.CategoryRepository;
import com.example.buoi1.responses.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{
    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Category> getAllCategories() {

        return categoryRepository.findAll();
    }

    @Override
    public Category saveCategory(CategoryDTO categoryDTO) {
        Category category = Category.builder()
                .name(categoryDTO.getCategoryName())
                .build();

        return categoryRepository.save(category);
    }


    @Override
    public Category updateCategory(Long id, CategoryDTO categoryDTO) {
        Category category = getCategoryById(id);
        category.setName(categoryDTO.getCategoryName());
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Page<CategoryResponse> getAllCategories1(PageRequest pageRequest) {
        return categoryRepository.findAll(pageRequest).map(category -> CategoryResponse.fromCategory(category));
    }
}
