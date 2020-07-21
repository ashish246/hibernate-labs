package com.hibernate.demo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hibernate.model.Employee1;
import com.hibernate.util.HibernateUtil;

/**
 * Have a look at the output and compare it with the output from the XML based
 * configuration, you will notice some differences. For example, we are not
 * setting connection pool size for annotation based configuration, so it’s
 * setting to default value 20.
 * 
 * @author Administrator
 *
 */
public class HibernateAnnotationMain {

	public static void main(String[] args) {
		Employee1 emp = new Employee1();
		emp.setName("Ashish");
		emp.setRole("Developer");
		emp.setInsertTime(new Date());

		// Get Session
		SessionFactory sessionFactory = HibernateUtil
				.getSessionAnnotationFactory();
		Session session = sessionFactory.getCurrentSession();
		// start transaction
		session.beginTransaction();
		// Save the Model object
		session.save(emp);
		// Commit transaction
		session.getTransaction().commit();
		System.out.println("Employee ID=" + emp.getId());

		// terminate session factory, otherwise program won't end
		sessionFactory.close();
	}

}