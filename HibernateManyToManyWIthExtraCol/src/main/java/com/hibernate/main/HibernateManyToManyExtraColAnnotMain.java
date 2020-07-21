package com.hibernate.main;

import java.util.Date;

import org.hibernate.Session;

import com.hibernate.model.Category1;
import com.hibernate.model.Stock1;
import com.hibernate.model.StockCategory1;
import com.hibernate.util.HibernateAnnotationUtil;

public class HibernateManyToManyExtraColAnnotMain {
	public static void main(String[] args) {
		System.out
				.println("Hibernate many to many - join table + extra column (Annotation)");
		Session session = HibernateAnnotationUtil.getSessionFactory().openSession();

		session.beginTransaction();

		Stock1 stock = new Stock1();
		stock.setStockCode("7050");
		stock.setStockName("PADINII");

		Category1 category1 = new Category1("CONSUMER", "CONSUMER COMPANY");
		// new category, need save to get the id first
		session.save(category1);

		// Category category1 = (Category)session.get(Category.class, 8);

		StockCategory1 stockCategory = new StockCategory1();

		stockCategory.setStock(stock);
		stockCategory.setCategory(category1);
		stockCategory.setCreatedDate(new Date());
		stockCategory.setCreatedBy("system");

		stock.getStockCategories().add(stockCategory);

		session.save(stock);

		session.getTransaction().commit();
		
		System.out.println("Done");
	}
}
