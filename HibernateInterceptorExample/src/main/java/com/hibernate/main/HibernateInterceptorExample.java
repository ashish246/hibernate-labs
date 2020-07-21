package com.hibernate.main;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.interceptor.AuditLogInterceptor;
import com.hibernate.model.Stock;
import com.hibernate.util.HibernateUtil;

public class HibernateInterceptorExample {
	public static void main(String[] args) {

		Session session = null;
		Transaction tx = null;

		try {

			AuditLogInterceptor interceptor = new AuditLogInterceptor();

			session = HibernateUtil.getSessionFactory().withOptions()
					.interceptor(interceptor).openSession();
			
			interceptor.setSession(session);

			// test insert
			tx = session.beginTransaction();
			
			Stock stockInsert = new Stock();
			stockInsert.setStockCode("1111");
			stockInsert.setStockName("Ashish1");
			session.saveOrUpdate(stockInsert);
			tx.commit();

			// test update
			tx = session.beginTransaction();
			Query query = session
					.createQuery("from Stock where stockCode = '1111'");
			Stock stockUpdate = (Stock) query.list().get(0);
			stockUpdate.setStockName("ashish-update");
			session.saveOrUpdate(stockUpdate);
			tx.commit();

			// test delete
			tx = session.beginTransaction();
			session.delete(stockUpdate);
			tx.commit();

		} catch (RuntimeException e) {
			try {
				tx.rollback();
			} catch (RuntimeException rbe) {
				// log.error("Couldn’t roll back transaction", rbe);
			}
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

}
