package com.hibernate.main;

import java.util.List;

import org.hibernate.Filter;
import org.hibernate.Query;
import org.hibernate.Session;

import com.hibernate.model.Student1;
import com.hibernate.util.HibernateAnnotationUtil;

public class HibernateDataFilterAnnotationExample {

	public static void main(String[] args) {

		Session session = HibernateAnnotationUtil.getSessionFactory().openSession();

		session.beginTransaction();

		Filter filter = session.enableFilter("studentFilter");
		filter.setParameter("studentFilterID", 3);

		Query query = session.createQuery("from Student1");

		List<?> list = query.list();

		for (int i = 0; i < list.size(); i++) {

			Student1 student = (Student1) list.get(i);
			System.out.println(student);
		}

		session.getTransaction().commit();
		
		session.close();
	}

}
