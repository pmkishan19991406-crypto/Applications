package com.kishan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kishan.model.Employee;
import com.kishan.repository.IEmployeeRepository;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    private IEmployeeRepository repo;

    @Autowired
    public EmployeeServiceImpl(IEmployeeRepository repo) {
        this.repo = repo;
    }

    @Override
    public Employee save(Employee emp) {
        return repo.save(emp);
    }

    @Override
    public Employee findById(Integer eid) {

        if (repo.existsById(eid)) {
            return repo.findById(eid).get();
        } else {
            throw new RuntimeException(
                    "Employee not found for the given id: " + eid);
        }
    }

    @Override
    public Employee updateById(Integer eid, Employee emp) {

        Employee existingEmployee = repo.findById(eid)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Employee not found for the given id: " + eid));

        existingEmployee.setName(emp.getName());
        existingEmployee.setEmail(emp.getEmail());
        existingEmployee.setDepartment(emp.getDepartment());
        existingEmployee.setSalary(emp.getSalary());

        return repo.save(existingEmployee);
    }

    @Override
    public List<Employee> findAll() {
        return repo.findAll();
    }

    @Override
    public void deleteById(Integer eid) {

        if (!repo.existsById(eid)) {
            throw new RuntimeException(
                    "Employee not found for the given id: " + eid);
        }

        repo.deleteById(eid);
    }

    @Override
    public void deleteAll() {
        repo.deleteAll();
    }
}