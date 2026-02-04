package com.saineethu.expense_tracker.config;

import com.saineethu.expense_tracker.entity.Category;
import com.saineethu.expense_tracker.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CategorySeeder {


    @Bean
    CommandLineRunner seedCategories(CategoryRepository categoryRepository){
        return args -> {

            List<String> categories = List.of(
                    "Food",
                    "Rent",
                    "Transport",
                    "Entertainment",
                    "Utilities",
                    "Shopping",
                    "Health",
                    "Travel",
                    "Others"
            );

            for (String name : categories){
                categoryRepository.findByName(name)
                        .orElseGet(()-> categoryRepository.save(new Category(name)));
            }
        };
    }
}
