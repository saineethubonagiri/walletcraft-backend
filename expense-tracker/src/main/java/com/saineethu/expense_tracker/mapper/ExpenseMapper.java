package com.saineethu.expense_tracker.mapper;

import com.saineethu.expense_tracker.dto.ExpenseCreateRequest;
import com.saineethu.expense_tracker.dto.ExpenseDto;
import com.saineethu.expense_tracker.dto.ExpenseUpdateRequest;
import com.saineethu.expense_tracker.entity.Category;
import com.saineethu.expense_tracker.entity.Expense;
import com.saineethu.expense_tracker.entity.User;

import java.util.UUID;

public class ExpenseMapper {

    private ExpenseMapper() {}
    public static ExpenseDto toDto(Expense e){
        if(e == null) return null;
        return new ExpenseDto(
                e.getId(),
                e.getUser() == null ? null : e.getUser().getId(),
                e.getAmount(),
                e.getDescription(),
                CategoryMapper.toDto(e.getCategory()),
                e.getDate()
        );
    }

    /* Build an Expense entity from create request.
    * Requires resolved User and Category entities (fetched from DB by service).
    *
    * */

    public static Expense fromCreateRequest(ExpenseCreateRequest req, User user, Category category){
        if(req == null) return null;
        Expense e = new Expense();
       // e.setId(UUID.randomUUID());
        e.setUser(user);
        e.setCategory(category);
        e.setAmount(req.getAmount());
        e.setDescription(req.getDescription());
        e.setDate(req.getDate());
        return e;
    }

    /*
    * Apply updates from ExpenseUpdateRequest to an existing Expense entity.
    * The caller should fetch the entity and supply resolved Category if categoryId changes.
    *
    * */
    public static Expense applyUpdate(Expense existing, ExpenseUpdateRequest req, Category newCategory){
        if (existing == null || req == null) return existing;
        if(req.getAmount() != null) existing.setAmount(req.getAmount());
        existing.setDescription(req.getDescription()); // allow null -> clears field if needed
        if(newCategory != null) existing.setCategory(newCategory);
        if(req.getDate() != null) existing.setDate(req.getDate());
        return existing;
    }
}
