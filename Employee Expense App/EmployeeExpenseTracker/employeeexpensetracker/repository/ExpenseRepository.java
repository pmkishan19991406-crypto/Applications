package com.kishan.employeeexpensetracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kishan.employeeexpensetracker.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByStatus(Expense.Status status);

}
