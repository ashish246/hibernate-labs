package com.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernate.model.Address;
import com.hibernate.model.Employee;
import com.hibernate.util.HibernateUtil;

/**
 * As the method name suggests, hibernate save() can be used to save entity to
 * database. We can invoke this method outside a transaction, that�s why I don�t
 * like this method to save data. If we use this without transaction and we have
 * cascading between entities, then only the primary entity gets saved unless we
 * flush the session. Few important points that we can confirm from above output
 * are:
 * 
 * � We should avoid save outside transaction boundary, otherwise mapped
 * entities will not be saved causing data inconsistency. It�s very normal to
 * forget flushing the session because it doesn�t throw any exception or
 * warnings.
 * 
 * � Hibernate save method returns the generated id immediately, this is
 * possible because primary object is saved as soon as save method is invoked.
 * 
 * � If there are other objects mapped from the primary object, they gets saved
 * at the time of committing transaction or when we flush the session.
 * 
 * � For objects that are in persistent state, save updates the data through
 * update query. Notice that it happens when transaction is committed. If there
 * are no changes in the object, there wont be any query fired. If you will run
 * above program multiple times, you will notice that update queries are not
 * fired next time because there is no change in the column values.
 * 
 * � Hibernate save load entity object to persistent context, if you will update
 * the object properties after the save call but before the transaction is
 * committed, it will be saved into database.
 * 
 * @author Administrator
 *
 */
public class HibernateSaveExample {

	public static void main(String[] args) {

		// Prep Work
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		// save example - without transaction
		Session session = sessionFactory.openSession();
		Employee emp = getTestEmployee();
		long id = (Long) session.save(emp);
		System.out.println("1. Employee save called without transaction, id="
				+ id);
		session.flush(); // address will not get saved without this
		System.out.println("*****");

		// save example - with transaction
		Transaction tx1 = session.beginTransaction();
		Session session1 = sessionFactory.openSession();
		Employee emp1 = getTestEmployee();
		long id1 = (Long) session1.save(emp1);
		System.out.println("2. Employee save called with transaction, id="
				+ id1);
		System.out.println("3. Before committing save transaction");
		tx1.commit();
		System.out.println("4. After committing save transaction");
		System.out.println("*****");

		// save example - existing row in table
		Session session6 = sessionFactory.openSession();
		Transaction tx6 = session6.beginTransaction();
		Employee emp6 = (Employee) session6.load(Employee.class, new Long(20));

		// update some data
		System.out.println("Employee Details=" + emp6);
		emp6.setName("New Name");
		emp6.getAddress().setCity("New City");

		long id6 = (Long) session6.save(emp6);
		emp6.setName("New Name1"); // will get updated in database
		System.out.println("5. Employee save called with transaction, id="
				+ id6);
		System.out.println("6. Before committing save transaction");
		tx6.commit();
		System.out.println("7. After committing save transaction");
		System.out.println("*****");

		// Close resources
		sessionFactory.close();

	}

	public static Employee getTestEmployee() {
		Employee emp = new Employee();
		Address add = new Address();
		emp.setName("Test Emp");
		emp.setSalary(1000);
		add.setAddressLine1("Test address1");
		add.setCity("Test City");
		add.setZipcode("12121");
		emp.setAddress(add);
		add.setEmployee(emp);
		return emp;
	}
}