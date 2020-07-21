package com.hibernate.main;

import java.util.Date;

import org.hibernate.Session;

import com.hibernate.model.Category;
import com.hibernate.model.Stock;
import com.hibernate.model.StockCategory;
import com.hibernate.util.HibernateUtil;

public class HibernateManyToManyExtraColMain {
	public static void main(String[] args) {
		System.out
				.println("Hibernate many to many - join table + extra column (XML)");
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		Stock stock = new Stock();
		stock.setStockCode("7053");
		stock.setStockName("PADINA");

		Category category1 = new Category("CONSUMER", "CONSUMER COMPANY");
		// new category, need save to get the id first
		session.save(category1);

		// Category category1 = (Category)session.get(Category.class, 8);

		StockCategory stockCategory = new StockCategory();

		stockCategory.setStock(stock);
		stockCategory.setCategory(category1);
		stockCategory.setCreatedDate(new Date());
		stockCategory.setCreatedBy("ashish");

		stock.getStockCategories().add(stockCategory);

		session.save(stock);

		session.getTransaction().commit();
		
		System.out.println("Done");
	}
}
