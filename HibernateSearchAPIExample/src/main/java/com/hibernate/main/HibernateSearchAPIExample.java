package com.hibernate.main;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import com.hibernate.model.Book;
import com.hibernate.util.HibernateSessionWithThreadLocal;

public class HibernateSearchAPIExample {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		// Prep work
		Session session = HibernateSessionWithThreadLocal.getSession();

		// Once you have added the above properties and annotations it is time
		// to trigger an initial batch index of your books.
		FullTextSession fullTextSessionInitial = Search
				.getFullTextSession(session);
		try {
			fullTextSessionInitial.createIndexer().startAndWait();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		try {
			Book book = new Book();
			book.setPublicationDate(new Date());
			book.setSubtitle("jsp");
			book.setTitle("tech");

			session.saveOrUpdate(book);

			FullTextSession fullTextSession = org.hibernate.search.Search
					.getFullTextSession(session);

			Transaction tx = fullTextSession.beginTransaction();

			QueryBuilder qb = fullTextSession.getSearchFactory()
					.buildQueryBuilder().forEntity(Book.class).get();

			org.apache.lucene.search.Query query = qb.keyword()
					.onFields("title", "subtitle").matching("jsp")
					.createQuery();

			// wrap Lucene query in a org.hibernate.Query
			org.hibernate.Query hibQuery = fullTextSession.createFullTextQuery(
					query, Book.class);

			// execute search
			List result = hibQuery.list();

			Iterator<Book> it = result.iterator();

			while (it.hasNext()) {
				Book book1 = (Book) it.next();
				System.out.println(book1);
			}

			tx.commit();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			session.close();
		}

	}

}