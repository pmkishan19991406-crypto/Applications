package com.kishan.model;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
	
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer eid;

	    @NotNull
	    @Column(name = "name", nullable = false)
	    private String name;

	    @NotNull
	    @Column(name = "email", nullable = false, unique = true)
	    private String email;

	    @NotNull
	    @Column(name = "department", nullable = false)
	    private String department;

	    @NotNull
	    @Column(name = "salary", nullable = false)
	    private Double salary;
	    
	    public Employee() {
			System.out.println("Employee Zero Argument Constructor");
		}

		public Integer getEid() {
			return eid;
		}

		public void setEid(Integer eid) {
			this.eid = eid;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getDepartment() {
			return department;
		}

		public void setDepartment(String department) {
			this.department = department;
		}

		public Double getSalary() {
			return salary;
		}

		public void setSalary(Double salary) {
			this.salary = salary;
		}

		@Override
		public String toString() {
			return "Employee [eid=" + eid + ", name=" + name + ", email=" + email + ", department=" + department
					+ ", salary=" + salary + "]";
		}
	    


}
