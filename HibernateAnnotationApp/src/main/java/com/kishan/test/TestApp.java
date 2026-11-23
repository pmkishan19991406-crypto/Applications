package com.kishan.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.kishan.model.Employee;

public class TestApp {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");
		SessionFactory factory = conf.buildSessionFactory();
		System.out.println("Implementation calss:" +factory.getClass().getName());
		Session session = factory.openSession();
		System.out.println("Implementation calss:"+session.getClass().getName());
		Transaction tx = session.beginTransaction();
		
		Employee emp = new Employee();
		emp.setEname("Sachin");
		emp.setEage(50);
		emp.setEaddress("MI");
		session.save(emp);
		tx.commit();
		System.out.println(emp);
		
		session.close();
		factory.close();

	}

}
