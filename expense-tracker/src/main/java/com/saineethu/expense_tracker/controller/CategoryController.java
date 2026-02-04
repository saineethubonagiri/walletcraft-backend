package com.saineethu.expense_tracker.controller;

import com.saineethu.expense_tracker.entity.Category;
import com.saineethu.expense_tracker.repository.CategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories(){
        System.out.println("Categories endpoint hit");
        return categoryRepository.findAll();
    }
}
