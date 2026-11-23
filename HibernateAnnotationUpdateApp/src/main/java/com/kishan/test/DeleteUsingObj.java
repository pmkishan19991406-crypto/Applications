package com.kishan.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.kishan.model.Employee;

public class DeleteUsingObj {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction transaction=null;
		transaction = session.beginTransaction();
		int id=7;
		Employee employee = session.get(Employee.class, id);
		if (employee!=null) {
			System.out.println(employee);
			if (transaction!=null) {
				
				session.delete(employee);
				transaction.commit();
				System.out.println("Employee Record Delted for the id:" +employee.getEid() );
			} 
		}
		
		
		
		session.close();
		
		
		

	}

}
