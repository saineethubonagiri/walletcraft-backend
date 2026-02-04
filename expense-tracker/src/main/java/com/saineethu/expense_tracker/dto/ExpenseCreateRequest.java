package com.saineethu.expense_tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseCreateRequest {
   // private UUID userId;
    private BigDecimal amount;
    private String description;
    private UUID categoryId;
    private LocalDate date;
}
