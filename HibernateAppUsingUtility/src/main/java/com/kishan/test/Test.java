package com.kishan.test;

import org.hibernate.SessionFactory;

import com.kishan.Factory.HibernateUtil;

public class Test {

	public static void main(String[] args) {
	
		SessionFactory factory1= HibernateUtil.getSessionFactory();
		SessionFactory factory2= HibernateUtil.getSessionFactory();
		System.out.println(factory1);
		System.out.println(factory2);
		System.out.println(factory1.hashCode());
		System.out.println(factory2.hashCode());
		System.out.println(factory1==factory2);
		
		

	}

}
