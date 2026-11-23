package com.kishan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kishan.model.Employee;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {

}
