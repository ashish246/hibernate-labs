package com.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hibernate.model.Employee;
import com.hibernate.model.Name;
import com.hibernate.util.HibernateUtil;

/**
 * @SecondaryTables is the annotation in hibernate by which an entity can map
 *                  more than one table to fetch the data. The entity which is
 *                  fetching data should have @SecondaryTables annotations. It
 *                  associates secondary table on the basis of primary and
 *                  foreign key and also on the basis of unique constrains. In
 *                  the example I have taken two entity one is student and
 *                  another is name. Student entity will fetch secondary entity
 *                  name on the basis of primary key.
 * 
 * @author Administrator
 *
 */
public class HibernateSecondaryTableAnnot {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		// Prep work
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();

		session.beginTransaction();

		Employee s = new Employee(1);
		session.persist(s);

		Name n = new Name(1, "Ankita");
		session.persist(n);

		session.getTransaction().commit();
		session.refresh(s);
		session.refresh(n);

		Employee ob = (Employee) session.get(Employee.class, new Integer(1));
		String name = ob.getName();

		System.out.println(name);
	}
}