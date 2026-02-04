package com.saineethu.expense_tracker.service;

import com.saineethu.expense_tracker.dto.ExpenseCreateRequest;
import com.saineethu.expense_tracker.dto.ExpenseDto;
import com.saineethu.expense_tracker.dto.ExpenseUpdateRequest;

import java.util.List;
import java.util.UUID;

public interface ExpenseService {

    ExpenseDto createExpense(String email, ExpenseCreateRequest request);

    ExpenseDto updateExpense(String email, UUID expenseId, ExpenseUpdateRequest request);

    void deleteExpense(String email, UUID expenseId);

    List<ExpenseDto> getExpenses(String email);

    List<ExpenseDto> getExpensesByCategory(String email, UUID categoryId);

    List<ExpenseDto> getExpensesByDateRange(String email, String startDate, String endDate);
}
