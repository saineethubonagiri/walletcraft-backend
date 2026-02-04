package com.saineethu.expense_tracker.mapper;

import com.saineethu.expense_tracker.dto.CategoryDto;
import com.saineethu.expense_tracker.entity.Category;

import java.util.UUID;

public class CategoryMapper {

    private CategoryMapper(){}

    public static CategoryDto toDto(Category category){
        if(category == null) return null;
        return new CategoryDto(category.getId(), category.getName());

    }

    public static Category fromName(String name){
        if(name == null) return null;
        Category c = new Category();
        //c.setId(UUID.randomUUID());
        c.setName(name);
        return c;
    }

    //if you need to create from a DTO
    public static Category fromDto(CategoryDto dto){
        if(dto == null) return null;
        Category c = new Category();
        //c.setId(dto.getId() == null ? UUID.randomUUID() : dto.getId());
        c.setId(dto.getId());
        c.setName(dto.getName());
        return c;
    }
}
