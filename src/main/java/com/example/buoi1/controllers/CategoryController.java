package com.example.buoi1.controllers;

import com.example.buoi1.dtos.CategoryDTO;
import com.example.buoi1.models.Category;
import com.example.buoi1.responses.CategoryListResponse;
import com.example.buoi1.responses.CategoryResponse;
import com.example.buoi1.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/list")
    public ResponseEntity<CategoryListResponse> getAllCategoriesPage(@RequestParam("page") int page, @RequestParam("limit") int limit){
        System.out.print("OK");
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by("createdAt").descending());
        Page<CategoryResponse> categoryResponsePage = categoryService.getAllCategories1(pageRequest);

        int totalPages = categoryResponsePage.getTotalPages();
        List<CategoryResponse> categoryResponses = categoryResponsePage.getContent();
        return ResponseEntity.ok(CategoryListResponse.builder()
                .categories(categoryResponses)
                .totalPage(totalPages)
                .build());
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return "Deleted: " + id;
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO){
        return categoryService.updateCategory(id, categoryDTO);
    }
    @PostMapping("/insert1")
    public ResponseEntity<?> postCategory1(@Valid @RequestBody CategoryDTO categoryDTO, BindingResult result) {
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(errors);
        }

        categoryService.saveCategory(categoryDTO);
        return ResponseEntity.ok("insert"+categoryDTO);
    }
    @PostMapping("/insert")
    public String postCategory(@Valid @RequestBody CategoryDTO categoryDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<String> errors = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
            return errors.toString();
        }
        categoryService.saveCategory(categoryDTO);
        return categoryDTO.toString();
    }

}
