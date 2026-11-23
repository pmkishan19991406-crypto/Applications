package com.kishan.employeeexpensetracker.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kishan.employeeexpensetracker.entity.Expense;
import com.kishan.employeeexpensetracker.repository.ExpenseRepository;

@Service
public class ExpenseServiceImpl implements IExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public Expense submitExpense(Expense expense) {

        expense.setAppliedDate(LocalDateTime.now());
        expense.setStatus(Expense.Status.PENDING);

        return expenseRepository.save(expense);
    }

    @Override
    public List<Expense> getAllExpenses() {

        return expenseRepository.findAll();
    }

    @Override
    public List<Expense> getExpensesByStatus(Expense.Status status) {

        return expenseRepository.findByStatus(status);
    }

    @Override
    public Expense updateExpenseStatus(Long id, Expense.Status status) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() ->
                    new IllegalArgumentException("Expense not found"));

        expense.setStatus(status);

        return expenseRepository.save(expense);
    }
}