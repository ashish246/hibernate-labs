package com.hibernate.main;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hibernate.model.College;
import com.hibernate.model.Student;
import com.hibernate.util.HibernateUtil;

/**
 * @ElementCollection is the feature which gets the columns values from another
 *                    table without mapping two tables. We have taken two entity
 *                    student and college. In college entity, we will fetch
 *                    students without mapping student and college entity. @CollectionTable
 *                    will join the two tables for the given primary and foreign
 *                    key.
 * @author Administrator
 *
 */
public class HibernateElementCollectionAnnot {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		// Prep work
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();

		session.getTransaction().begin();

		College c = new College(1, "S.S.P.C");
		session.persist(c);
		Student s1 = new Student(1, "Atul", 1);
		session.persist(s1);
		Student s2 = new Student(2, "Saurabh", 1);
		session.persist(s2);

		session.getTransaction().commit();

		session.refresh(c);
		College ob = (College) session.get(College.class, new Integer(1));
		Set<String> names = ob.getStudents();
		for (String s : names) {
			System.out.println(s);
		}
	}
}