package com.hibernate.model;

import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "stock_category")
@AssociationOverrides({
		@AssociationOverride(name = "pk.stock", joinColumns = @JoinColumn(name = "STOCK_ID")),
		@AssociationOverride(name = "pk.category", joinColumns = @JoinColumn(name = "CATEGORY_ID")) })
public class StockCategory1 implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private StockCategoryId1 pk = new StockCategoryId1();
	private Date createdDate;
	private String createdBy;

	public StockCategory1() {
	}

	@EmbeddedId
	public StockCategoryId1 getPk() {
		return pk;
	}

	public void setPk(StockCategoryId1 pk) {
		this.pk = pk;
	}

	@Transient
	public Stock1 getStock() {
		return getPk().getStock();
	}

	public void setStock(Stock1 stock) {
		getPk().setStock(stock);
	}

	@Transient
	public Category1 getCategory() {
		return getPk().getCategory();
	}

	public void setCategory(Category1 category) {
		getPk().setCategory(category);
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_DATE", nullable = false, length = 10)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "CREATED_BY", nullable = false, length = 10)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		StockCategory1 that = (StockCategory1) o;

		if (getPk() != null ? !getPk().equals(that.getPk())
				: that.getPk() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}
}