package com.kishan.test;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kishan.Factory.HibernateUtil;
import com.kishan.model.Person;

public class SaveApp {
	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = null;
		boolean flag= false;
		try {
			transaction=session.beginTransaction();
			Person person = new Person();
			person.setPname("Kishan P M");
			person.setDob(LocalDateTime.of(1999, 06, 14, 14, 33));
			person.setDom(LocalDateTime.of(2028, 06, 20, 9, 30));//y-m-d-h-m
			person.setDoj(LocalDate.now());
			// text - Clob
			String txt="Welcome to My App";
			char[] charArray = txt.toCharArray();
			person.setMyData(charArray);
			
			//Blob- img
			
			String filePath = "C:\\Users\\pmkishan\\Downloads\\hibernate.jpg";
			byte[] b = Files.readAllBytes(Paths.get(filePath));

			person.setImg(b);

			session.persist(person);
			session.save(person);
			flag=true;
			
			
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}finally {
			if (flag) {
				transaction.commit();
				System.out.println("Record Saved to Database..");
			} else {
				transaction.rollback();
				System.out.println("Record failed Saved to Database..");
			}
			
		}
	}

}
