
package com.kishan.employeeexpensetracker.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.kishan.employeeexpensetracker.entity.Expense;
import com.kishan.employeeexpensetracker.service.IExpenseService;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "http://localhost:5173")
public class ExpenseController {

    private final IExpenseService expenseService;

    public ExpenseController(IExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    // Employee submits expense
    @PostMapping("/submit")
    public ResponseEntity<?> submitExpense(
            @RequestBody Expense expense,
            Authentication authentication) {

        try {

            String email = authentication.getName();

            Expense savedExpense =
                    expenseService.submitExpense(expense, email);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(savedExpense);

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    // Manager views all expenses
    @GetMapping("/all")
    public ResponseEntity<List<Expense>> getAllExpenses() {

        List<Expense> expenses = expenseService.getAllExpenses();

        return ResponseEntity.ok(expenses);
    }

    // Employee views own expenses
    @GetMapping("/my")
    public ResponseEntity<List<Expense>> getMyExpenses(
            Authentication authentication) {

        String email = authentication.getName();

        List<Expense> expenses =
                expenseService.getMyExpenses(email);

        return ResponseEntity.ok(expenses);
    }

    // Manager filters expenses by status
    @GetMapping("/status")
    public ResponseEntity<List<Expense>> getExpensesByStatus(
            @RequestParam Expense.Status status) {

        List<Expense> expenses =
                expenseService.getExpensesByStatus(status);

        return ResponseEntity.ok(expenses);
    }

    // Manager approves or rejects expense
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateExpenseStatus(
            @PathVariable Long id,
            @RequestParam Expense.Status status) {

        try {

            Expense updatedExpense =
                    expenseService.updateExpenseStatus(id, status);

            return ResponseEntity.ok(updatedExpense);

        } catch (IllegalArgumentException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}
