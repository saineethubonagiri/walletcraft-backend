package com.saineethu.expense_tracker.controller;

import com.saineethu.expense_tracker.dto.ExpenseCreateRequest;
import com.saineethu.expense_tracker.dto.ExpenseDto;
import com.saineethu.expense_tracker.dto.ExpenseUpdateRequest;
import com.saineethu.expense_tracker.security.JwtUtil;
import com.saineethu.expense_tracker.service.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/expenses")

public class ExpenseController {
    private final ExpenseService expenseService;
    private final JwtUtil jwtUtil;

    public ExpenseController(ExpenseService expenseService, JwtUtil jwtUtil) {
        this.expenseService = expenseService;
        this.jwtUtil = jwtUtil;
    }

    private String getEmailFromToken(String token) {
        return jwtUtil.extractEmail(token);
    }

    @PostMapping
    public ResponseEntity<ExpenseDto> addExpense(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody ExpenseCreateRequest request) {

        String email = getEmailFromToken(authHeader.substring(7));
        //return ResponseEntity.ok(expenseService.createExpense(email, request));
        ExpenseDto created = expenseService.createExpense(email, request);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseDto> updateExpense(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable UUID id,
            @RequestBody ExpenseUpdateRequest request) {

        String email = getEmailFromToken(authHeader.substring(7));
        return ResponseEntity.ok(expenseService.updateExpense(email, id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable UUID id) {

        String email = getEmailFromToken(authHeader.substring(7));
        expenseService.deleteExpense(email, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ExpenseDto>> getAllExpenses(
            @RequestHeader("Authorization") String authHeader) {

        String email = getEmailFromToken(authHeader.substring(7));
        return ResponseEntity.ok(expenseService.getExpenses(email));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ExpenseDto>> getExpensesByCategory(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable UUID categoryId) {

        String email = getEmailFromToken(authHeader.substring(7));
        return ResponseEntity.ok(expenseService.getExpensesByCategory(email, categoryId));
    }

    @GetMapping("/range")
    public ResponseEntity<List<ExpenseDto>> getExpensesByDateRange(
            @RequestHeader("Authorization") String authHeader,
            @RequestParam String startDate,
            @RequestParam String endDate) {

        String email = getEmailFromToken(authHeader.substring(7));
        return ResponseEntity.ok(expenseService.getExpensesByDateRange(email, startDate, endDate));
    }


}
