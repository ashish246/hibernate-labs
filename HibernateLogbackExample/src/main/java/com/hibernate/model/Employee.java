package com.hibernate.model;

import java.util.Date;

/**
 * Employee is a simple Java Bean class and we will use XML based configuration
 * for providing it’s mapping details.
 * 
 * @author Administrator
 *
 */
public class Employee {

	private int id;
	private String name;
	private String role;
	private Date insertTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

}