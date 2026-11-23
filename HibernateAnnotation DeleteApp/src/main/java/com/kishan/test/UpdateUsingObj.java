package com.kishan.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.kishan.model.Employee;

public class UpdateUsingObj {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction transaction=null;
		transaction = session.beginTransaction();
		int id=1;
		Employee employee = session.get(Employee.class, id);
		if (transaction!=null) {
			employee.setEname("Kishan ");
			session.update(employee);
			transaction.commit();
			System.out.println("Employee Record Updated for the id:" +employee.getEid() );
		} 
		
		
		session.close();
		
		
		

	}

}
