package com.kishan.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.kishan.model.Employee;

public class DeleteWithoutObj {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction transaction=null;
		transaction = session.beginTransaction();
		if (transaction!=null) {
			Employee employee = new Employee();
			employee.setEid(9);
			session.delete(employee);
			transaction.commit();
			System.out.println("Employee Record Dleted for the id:" +employee.getEid() );
		} 
		
		
		session.close();
		
		
		

	}

}
