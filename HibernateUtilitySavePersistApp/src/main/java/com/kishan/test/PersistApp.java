package com.kishan.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kishan.Factory.HibernateUtil;
import com.kishan.model.Employee;


public class PersistApp {


	public static void main(String[] args) {
		
		Session session=null;
		Transaction tx =null;
		try {
			SessionFactory factory = HibernateUtil.getSessionFactory();
			if (factory != null) {
				session = factory.openSession();
				if (session != null) {
					tx = session.beginTransaction();
					if (tx != null) {
						Employee employee = new Employee();
						employee.setEaddress("GT");
						employee.setEage(32);
						employee.setEname("Butler");
						session.persist(employee);
						tx.commit();
						System.out.println(employee);

					}

				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		
		

	}

}
