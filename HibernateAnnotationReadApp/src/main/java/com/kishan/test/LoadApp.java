package com.kishan.test;

import java.io.IOException;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.kishan.model.Employee;

public class LoadApp {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {
		
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		int pkId=40;
		// Give the id value if present print else no ObjectNotFoundException and  Zero Arugument Constructor is created
		Employee employee = session.load(Employee.class, pkId);
		try {
			if (employee!=null) {
				System.out.println("EID is:"+employee.getEid());
				System.in.read();
				// invoking getter method for non-id property values
				System.out.println("EName is:"+employee.getEname());
				System.out.println("EAge is:"+employee.getEage());
				System.out.println("EAddress is:"+employee.getEaddress());
				
			} 
		} catch (ObjectNotFoundException e) {
			System.out.println("Record not found for the id "+ pkId);
		}
		
		session.close();
		
		
		

	}

}
