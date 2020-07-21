package com.hibernate.main;

import java.util.List;

import org.hibernate.Filter;
import org.hibernate.Query;
import org.hibernate.Session;

import com.hibernate.model.Student;
import com.hibernate.util.HibernateUtil;

public class HibernateDataFilterExample {

	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		Filter filter = session.enableFilter("studentFilter");
		filter.setParameter("studentFilterID", 3);

		Query query = session.createQuery("from Student");

		List<?> list = query.list();

		for (int i = 0; i < list.size(); i++) {

			Student student = (Student) list.get(i);
			System.out.println(student);
		}

		session.getTransaction().commit();
		
		session.close();
	}

}
