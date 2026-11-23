
package com.kishan.employeeexpensetracker.service;

import java.util.List;

import com.kishan.employeeexpensetracker.entity.Expense;

public interface IExpenseService {

    Expense submitExpense(Expense expense, String email);

    List<Expense> getAllExpenses();

    List<Expense> getMyExpenses(String email);

    List<Expense> getExpensesByStatus(Expense.Status status);

    Expense updateExpenseStatus(Long id, Expense.Status status);
}
