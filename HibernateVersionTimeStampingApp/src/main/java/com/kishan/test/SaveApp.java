package com.kishan.test;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kishan.Factory.HibernateUtil;
import com.kishan.model.Bank;

public class SaveApp {

    public static void main(String[] args) {

       SessionFactory factory = HibernateUtil.getSessionFactory();
       
       boolean flag= false;
       Session session = factory.openSession();
       Transaction tx = null;
       Bank account=null;
       
       try {
    	   tx = session.beginTransaction();
    	   account = new Bank();
    	   account.setName("Kishan PM");
    	   account.setAccountType("saving");
    	   account.setBalance(55555.5f);
    	   session.save(account);
    	   flag=true;
		
	} catch (Exception e) {
		flag=false;
		e.printStackTrace();
	}
       finally {
    	   if (flag) {
    		   tx.commit();
    		   System.out.println("BANK id:"+account.getAccNo());
    		   System.out.println("BANK HolderName:"+account.getName());
    		   System.out.println("BANK Type:"+account.getAccountType());
    		   System.out.println("BANK BALANCE:"+account.getBalance());
    		   System.out.println("BANK Creation:"+account.getCreateDate());
    		   System.out.println("BANK LASTUPDATION:"+account.getLastUpdate());
    		   System.out.println("BANK Access Count:"+account.getCount());
			
		} else {
			tx.rollback();
		}
       }
       // Closing the resource
       HibernateUtil.closeSession(session);
       HibernateUtil.closeSessionFactory();
    }
}