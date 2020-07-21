package com.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hibernate.model.Animal;
import com.hibernate.model.Elephant;
import com.hibernate.model.Lion;
import com.hibernate.util.HibernateUtil;

public class HibernateEmbeddableExample {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		// Prep work
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Animal animal = new Animal("Lion A", "Africa");
		Lion lion = new Lion(1, animal);

		animal = new Animal("Elephnat A", "Asia");
		Elephant elephant = new Elephant(1, animal);

		session.save(lion);
		session.save(elephant);

		session.getTransaction().commit();
		session.close();

	}

}