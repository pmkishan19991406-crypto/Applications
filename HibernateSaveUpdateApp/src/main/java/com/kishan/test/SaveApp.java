package com.kishan.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kishan.Factory.HibernateUtil;
import com.kishan.model.Employee;

public class SaveApp {

    public static void main(String[] args) {

        Session session = null;
        Transaction tx = null;
        boolean flag = false;

        SessionFactory factory = HibernateUtil.getSessionFactory();

        try {

            if (factory != null) {

                session = factory.openSession();

                if (session != null) {

                    tx = session.beginTransaction();

                    Employee employee = new Employee();
                    employee.setEid(12);
                    employee.setEaddress("TN");
                    employee.setEage(25);
                    employee.setEname("Sai");

                    session.saveOrUpdate(employee);

                    flag = true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {

            if (tx != null) {
                if (flag) {
                    tx.commit();
                    System.out.println("Record saved/updated successfully");
                } else {
                    tx.rollback();
                }
            }

            // Close Session
            HibernateUtil.closeSession(session);

            // Close SessionFactory
            HibernateUtil.closeSessionFactory();
        }
    }
}