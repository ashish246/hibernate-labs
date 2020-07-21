package com.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernate.model.Employee;
import com.hibernate.util.HibernateUtil;

/**
 * Hibernate merge can be used to update existing values, however this method
 * create a copy from the passed entity object and return it. The returned
 * object is part of persistent context and tracked for any changes, passed
 * object is not tracked. This is the major difference with merge() from all
 * other methods. Let’s look at this with a simple program.
 * 
 * @author Administrator
 *
 */
public class HibernateMergeExample {

	public static void main(String[] args) {

		// Prep Work
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Employee emp = (Employee) session.load(Employee.class, new Long(101));
		System.out.println("Employee object loaded. " + emp);
		tx.commit();

		// merge example - data already present in tables
		emp.setSalary(25000);
		Transaction tx8 = session.beginTransaction();
		Employee emp4 = (Employee) session.merge(emp);
		System.out.println(emp4 == emp); // returns false
		emp.setName("Test");
		emp4.setName("Kumar");
		System.out.println("15. Before committing merge transaction");
		tx8.commit();
		System.out.println("16. After committing merge transaction");

		// Close resources
		sessionFactory.close();

	}

}