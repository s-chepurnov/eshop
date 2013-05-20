package org.shop.entity;

import java.io.Serializable;

public class Category implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int categoryId;
	private String categoryName;
	public Category(String name) {
		this.setCategoryName(name);
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName="	+ categoryName + "]";
	}
}
