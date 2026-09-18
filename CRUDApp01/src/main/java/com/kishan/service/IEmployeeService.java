package com.kishan.service;

import java.util.List;

import com.kishan.model.Employee;

public interface IEmployeeService {
	
	public Employee  save(Employee emp);
	
	public Employee findById(Integer eid);
	
	public Employee updateById(Integer eid , Employee emp);
	
	public List <Employee> findAll();
	
	public void deleteById(Integer eid);
	 
	 public void deleteAll();

}
