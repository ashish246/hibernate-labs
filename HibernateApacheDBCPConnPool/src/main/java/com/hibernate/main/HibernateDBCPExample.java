package com.hibernate.main;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hibernate.model.Employee;
import com.hibernate.util.HibernateUtil;

public class HibernateDBCPExample {

	private static Logger logger = LoggerFactory
			.getLogger(HibernateDBCPExample.class);

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