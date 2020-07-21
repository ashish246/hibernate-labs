package com.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernate.model.Employee;
import com.hibernate.util.HibernateUtil;

/**
 * Based on the above explanations we have following differences between get()
 * vs load():
 * 
 * 1. get() loads the data as soon as it’s called whereas load() returns a proxy
 * object and loads data only when it’s actually required, so load() is better
 * because it support lazy loading. For load(), query to DB is fired when we try
 * to access any property of the that entity.
 * 
 * 2. Since load() throws exception when data is not found, we should use it
 * only when we know data exists.
 * 
 * 3. We should use get() when we want to make sure data exists in the database.
 * 
 * @author Administrator
 *
 */
public class HibernateGetVsLoad {

	public static void main(String[] args) {

		// Prep Work
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		// Get Example
		Employee emp = (Employee) session.get(Employee.class, new Long(2));
		System.out.println("Employee get called");
		System.out.println("Employee ID= " + emp.getId());
		System.out.println("Employee Get Details:: " + emp + "\n");

		// load Example
		Employee emp1 = (Employee) session.load(Employee.class, new Long(1));
		System.out.println("Employee load called");
		System.out.println("Employee ID= " + emp1.getId());
		System.out.println("Employee load Details:: " + emp1 + "\n");

		// Close resources
		tx.commit();

		// /Another session
		Session sessionOther = sessionFactory.openSession();
		Transaction txOther = sessionOther.beginTransaction();
		// Get Example
		try {
			Employee empOther = (Employee) sessionOther.get(Employee.class,
					new Long(200));
			System.out.println("Employee get called");
			if (empOther != null) {
				System.out.println("Employee GET ID= " + empOther.getId());
				System.out.println("Employee Get Details:: " + empOther + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// load Example
		try {
			Employee empOther1 = (Employee) sessionOther.load(Employee.class,
					new Long(100));
			System.out.println("Employee load called");
			System.out.println("Employee LOAD ID= " + empOther1.getId());
			System.out.println("Employee load Details:: " + empOther1 + "\n");
		} catch (Exception e) {
			e.printStackTrace();
		}

		txOther.commit();

		sessionFactory.close();
	}
}