package com.hibernate.main;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernate.model.Employee;
import com.hibernate.util.HibernateUtil;

public class HibernateLog4JExample {

	static {
		System.out.println("Before log4j configuration");

		// If using configurator, then place log4j.xml in the project root
		// folder, else place it in /resource folder.
		// DOMConfigurator.configure("log4j.xml");

		System.out.println("After log4j configuration");
	}

	private static Logger logger = Logger
			.getLogger(HibernateLog4JExample.class);

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		// Prep work
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();

		// Get All Employees
		Transaction tx = session.beginTransaction();

		Query query = session.createQuery("from Employee");
		List<Employee> empList = query.list();
		for (Employee emp : empList) {
			logger.info("List of Employees::" + emp.getId() + ","
					+ emp.getName());
		}

		tx.commit();
		sessionFactory.close();
		logger.info("DONE");
	}

}