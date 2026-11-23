package com.kishan.employeeexpensetracker.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/submit")
    public ResponseEntity<Expense> submitExpense(
            @RequestBody Expense expense) {

        Expense savedExpense = expenseService.submitExpense(expense);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedExpense);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Expense>> getAllExpenses() {

        List<Expense> expenses = expenseService.getAllExpenses();

        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/status")
    public ResponseEntity<List<Expense>> getExpensesByStatus(
            @RequestParam Expense.Status status) {

        List<Expense> expenses =
                expenseService.getExpensesByStatus(status);

        return ResponseEntity.ok(expenses);
    }

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