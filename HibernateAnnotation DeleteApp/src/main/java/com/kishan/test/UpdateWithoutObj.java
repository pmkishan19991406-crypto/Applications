package com.kishan.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.kishan.model.Employee;

public class UpdateWithoutObj {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction transaction=null;
		transaction = session.beginTransaction();
		if (transaction!=null) {
			Employee employee = new Employee();
			employee.setEid(1);
			employee.setEname("Kishan P M");
			employee.setEage(27);
			employee.setEaddress("BNG");
			session.update(employee);
			transaction.commit();
			System.out.println("Employee Record Updated for the id:" +employee.getEid() );
		} 
		
		
		session.close();
		
		
		

	}

}
