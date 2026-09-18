package com.kishan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kishan.model.Employee;
import com.kishan.service.IEmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
    private  IEmployeeService service;

   
    @PostMapping
    public Employee save(@RequestBody Employee emp) {

        System.out.println("POST Request Received");
        System.out.println("Employee Name: " + emp.getName());
        System.out.println("Employee Email: " + emp.getEmail());

        Employee savedEmployee = service.save(emp);

        System.out.println("Employee Saved Successfully");
        System.out.println("Employee ID: " + savedEmployee.getEid());

        return savedEmployee;
    }

    @GetMapping("/{eid}")
    public Employee findById(@PathVariable Integer eid) {

        System.out.println("GET Request Received");
        System.out.println("Searching Employee ID: " + eid);

        Employee employee = service.findById(eid);

        System.out.println("Employee Found: " + employee.getName());

        return employee;
    }

    @GetMapping
    public List<Employee> findAll() {

        System.out.println("GET All Employees Request Received");

        List<Employee> employees = service.findAll();

        System.out.println("Total Employees: " + employees.size());

        return employees;
    }

    @PutMapping("/{eid}")
    public Employee updateById(@PathVariable Integer eid, @RequestBody Employee emp) {

        System.out.println("PUT Request Received");
        System.out.println("Updating Employee ID: " + eid);

        Employee updatedEmployee = service.updateById(eid, emp);

        System.out.println("Employee Updated Successfully");

        return updatedEmployee;
    }

    @DeleteMapping("/{eid}")
    public String deleteById(@PathVariable Integer eid) {

        System.out.println("DELETE Request Received");
        System.out.println("Deleting Employee ID: " + eid);

        service.deleteById(eid);

        System.out.println("Employee Deleted Successfully");

        return "Employee deleted successfully for id: " + eid;
    }

    @DeleteMapping
    public String deleteAll() {

        System.out.println("DELETE ALL Request Received");

        service.deleteAll();

        System.out.println("All Employees Deleted Successfully");

        return "All employees deleted successfully";
    }

   
}