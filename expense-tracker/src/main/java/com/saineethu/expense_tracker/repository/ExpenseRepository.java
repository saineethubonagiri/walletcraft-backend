package com.saineethu.expense_tracker.repository;

import com.saineethu.expense_tracker.entity.Category;
import com.saineethu.expense_tracker.entity.Expense;
import com.saineethu.expense_tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ExpenseRepository extends JpaRepository<Expense, UUID> {
    //All expenses for a user
    List<Expense> findByUser(User user);
    //By user and category
    List<Expense> findByUserAndCategory(User user, Category category);
    //By user and date range(for month filter)
    List<Expense> findByUserAndDateBetween(User user, LocalDate startDate, LocalDate endDate);

    //By user, category, and date range(for combined filters)
    List<Expense> findByUserAndCategoryAndDateBetween(User user, Category category, LocalDate startDate, LocalDate endDate);


}
