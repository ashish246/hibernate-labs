package com.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernate.model.Employee;
import com.hibernate.util.HibernateUtil;

/**
 * Hibernate persist is similar to save (with transaction) and it adds the
 * entity object to the persistent context, so any further changes are tracked.
 * If the object properties are changed before the transaction is committed or
 * session is flushed, it will also be saved into database.
 * 
 * Second difference is that we can use persist() method only within the
 * boundary of a transaction, so it’s safe and takes care of any cascaded
 * objects. Finally, persist doesn’t return anything so we need to use the
 * persisted object to get the generated identifier value.
 * 
 * @author Administrator
 *
 */
public class HibernatePersistExample {

	public static void main(String[] args) {

		// Prep Work
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		// persist example - with transaction
		Session session2 = sessionFactory.openSession();
		Transaction tx2 = session2.beginTransaction();
		Employee emp2 = HibernateSaveExample.getTestEmployee();
		session2.persist(emp2);
		System.out.println("Persist called");
		emp2.setName("Kumar"); // will be updated in database too
		System.out.println("Employee Name updated");
		System.out.println("8. Employee persist called with transaction, id="
				+ emp2.getId() + ", address id=" + emp2.getAddress().getId());
		tx2.commit();
		System.out.println("*****");

		// Close resources
		sessionFactory.close();

	}

}