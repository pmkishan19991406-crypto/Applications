package com.kishan.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.kishan.model.Employee;

public class GetApp {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		int pkId=100;
		// Give the id value if present print else no record found and no Zero Arugument Constructor is created
		Employee employee = session.get(Employee.class, pkId);
		if (employee!=null) {
			System.out.println(employee);
		} else {
			System.out.println("Record not found for the given id:"+pkId);
		}
		
		session.close();
		
		
		

	}

}
