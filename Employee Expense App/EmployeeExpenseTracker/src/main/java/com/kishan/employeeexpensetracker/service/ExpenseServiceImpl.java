package com.kishan.employeeexpensetracker.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kishan.employeeexpensetracker.entity.Expense;
import com.kishan.employeeexpensetracker.entity.User;
import com.kishan.employeeexpensetracker.repository.ExpenseRepository;
import com.kishan.employeeexpensetracker.repository.UserRepository;

@Service
public class ExpenseServiceImpl implements IExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public ExpenseServiceImpl(
            ExpenseRepository expenseRepository,
            UserRepository userRepository) {

        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    // Submit expense
    @Override
    public Expense submitExpense(Expense expense, String email) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        expense.setUser(user);
        expense.setAppliedDate(LocalDateTime.now());
        expense.setStatus(Expense.Status.PENDING);

        return expenseRepository.save(expense);
    }

    // Get all expenses
    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    // Get logged-in employee expenses
    @Override
    public List<Expense> getMyExpenses(String email) {
        return expenseRepository.findByUserEmail(email);
    }

    // Get expenses by status
    @Override
    public List<Expense> getExpensesByStatus(Expense.Status status) {
        return expenseRepository.findByStatus(status);
    }

    // Approve or reject expense
    @Override
    public Expense updateExpenseStatus(
            Long id,
            Expense.Status status) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() ->
                    new IllegalArgumentException("Expense not found"));

        if (status == Expense.Status.APPROVED) {

            expense.setStatus(Expense.Status.APPROVED);

        } else if (status == Expense.Status.REJECTED) {

            expense.setStatus(Expense.Status.REJECTED);

        } else {

            throw new IllegalArgumentException(
                    "Only APPROVED or REJECTED status is allowed");
        }

        return expenseRepository.save(expense);
    }
}
