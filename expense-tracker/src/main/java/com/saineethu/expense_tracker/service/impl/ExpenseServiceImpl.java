package com.saineethu.expense_tracker.service.impl;

import com.saineethu.expense_tracker.dto.ExpenseCreateRequest;
import com.saineethu.expense_tracker.dto.ExpenseDto;
import com.saineethu.expense_tracker.dto.ExpenseUpdateRequest;
import com.saineethu.expense_tracker.entity.Category;
import com.saineethu.expense_tracker.entity.Expense;
import com.saineethu.expense_tracker.entity.User;
import com.saineethu.expense_tracker.mapper.ExpenseMapper;
import com.saineethu.expense_tracker.repository.CategoryRepository;
import com.saineethu.expense_tracker.repository.ExpenseRepository;
import com.saineethu.expense_tracker.repository.UserRepository;
import com.saineethu.expense_tracker.service.ExpenseService;
//import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, UserRepository userRepository, CategoryRepository categoryRepository){

        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    //Helper to find user by email

    private User getUserByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("User not found"));
    }

    @Override
    public ExpenseDto createExpense(String email, ExpenseCreateRequest request){

        User user = getUserByEmail(email);
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Expense expense = new Expense();
        expense.setUser(user);
        expense.setCategory(category);
        expense.setAmount(request.getAmount());
        expense.setDescription(request.getDescription());
        expense.setDate(request.getDate());

        Expense saved = expenseRepository.save(expense);
        return ExpenseMapper.toDto(saved);
    }

    @Override
    public ExpenseDto updateExpense(String email, UUID expenseId, ExpenseUpdateRequest request) {
        User user = getUserByEmail(email);
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        // Check ownership
        if (!expense.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized to update this expense");
        }

        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            expense.setCategory(category);
        }
        if (request.getAmount() != null) expense.setAmount(request.getAmount());
        if (request.getDescription() != null) expense.setDescription(request.getDescription());
        if (request.getDate() != null) expense.setDate(request.getDate());

        Expense updated = expenseRepository.save(expense);
        return ExpenseMapper.toDto(updated);
    }
    @Override
    public void deleteExpense(String email, UUID expenseId) {
        User user = getUserByEmail(email);
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        if (!expense.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized to delete this expense");
        }

        expenseRepository.delete(expense);
    }

    @Override
    public List<ExpenseDto> getExpenses(String email) {
        User user = getUserByEmail(email);
        return expenseRepository.findByUser(user)
                .stream()
                .map(ExpenseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExpenseDto> getExpensesByCategory(String email, UUID categoryId) {
        User user = getUserByEmail(email);
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        return expenseRepository.findByUserAndCategory(user, category)
                .stream()
                .map(ExpenseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExpenseDto> getExpensesByDateRange(String email, String startDate, String endDate) {
        User user = getUserByEmail(email);
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        return expenseRepository.findByUserAndDateBetween(user, start, end)
                .stream()
                .map(ExpenseMapper::toDto)
                .collect(Collectors.toList());
    }


}
