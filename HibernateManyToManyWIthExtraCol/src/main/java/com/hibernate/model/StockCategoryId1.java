package com.hibernate.model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class StockCategoryId1 implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Stock1 stock;
	private Category1 category;

	@ManyToOne
	public Stock1 getStock() {
		return stock;
	}

	public void setStock(Stock1 stock) {
		this.stock = stock;
	}

	@ManyToOne
	public Category1 getCategory() {
		return category;
	}

	public void setCategory(Category1 category) {
		this.category = category;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		StockCategoryId1 that = (StockCategoryId1) o;

		if (stock != null ? !stock.equals(that.stock) : that.stock != null)
			return false;
		if (category != null ? !category.equals(that.category)
				: that.category != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = (stock != null ? stock.hashCode() : 0);
		result = 31 * result + (category != null ? category.hashCode() : 0);
		return result;
	}

}