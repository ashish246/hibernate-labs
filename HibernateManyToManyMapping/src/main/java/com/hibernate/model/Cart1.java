package com.hibernate.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "CART")
public class Cart1 {

	@Id
	@Column(name = "cart_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "cart_total")
	private double total;

	@ManyToMany(targetEntity = Item1.class)
	@JoinTable(name = "CART_ITEMS", joinColumns = { @JoinColumn(name = "cart_id") }, inverseJoinColumns = { @JoinColumn(name = "item_id") })
	@Cascade({CascadeType.SAVE_UPDATE})
	private Set<Item1> items;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Set<Item1> getItems() {
		return items;
	}

	public void setItems(Set<Item1> items) {
		this.items = items;
	}

	// Getter Setter methods
}