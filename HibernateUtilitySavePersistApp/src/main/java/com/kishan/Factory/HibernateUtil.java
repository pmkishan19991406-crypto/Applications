package com.kishan.Factory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

// SingleTon Class
public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	private HibernateUtil(){
		System.out.println("ZERO Argument HibernateUtil is used..");
	}
	
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory= new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		}
		return sessionFactory;
		
	}
	
	public void closeSessionFactory() {
		if (sessionFactory!=null) {
			sessionFactory.close();
		}
	}
	public void closeSession(Session session) {
		if (session!=null) {
			session.close();
		}
	}
	

}
