package com.saineethu.expense_tracker.repository;

import com.saineethu.expense_tracker.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    //Find category by name
    Optional<Category> findByName(String name);
}
