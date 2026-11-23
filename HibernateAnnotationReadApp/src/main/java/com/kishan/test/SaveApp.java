package com.kishan.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.kishan.model.Employee;

public class SaveApp {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		Employee employee = new Employee();
		employee.setEname("Jadeja");
		employee.setEage(38);
		employee.setEaddress("RR");
		
		session.save(employee);
		transaction.commit();
		session.close();
		
		
		

	}

}
