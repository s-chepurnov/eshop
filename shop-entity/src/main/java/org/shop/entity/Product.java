package org.shop.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	private int categoryId;
	private int supplierId;
	private int productId;
	private String SKU;
	private String productName;
	private String productDescription;
	private BigDecimal unitPrice;
	private float uninWeight;
	private float shippingWeight;
	private int unitsInStock;
	private String picture;
	private String flavour;
	private long UPC;
	private Date expirationDate;
	//for Enricher
	private Category category;
	private Supplier supplier;
	

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	//all neccesary fileds from DB
	public Product(int categoryId, int supplierId, String sKU,
			String productName, BigDecimal unitPrice, float uninWeight,
			float shippingWeight, int unitsInStock, String flavour, long uPC,
			Date expirationDate) {
		this.categoryId = categoryId;
		this.supplierId = supplierId;
		this.SKU = sKU;
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.uninWeight = uninWeight;
		this.shippingWeight = shippingWeight;
		this.unitsInStock = unitsInStock;
		this.flavour = flavour;
		this.UPC = uPC;
		this.expirationDate = expirationDate;
	}
	
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getSKU() {
		return SKU;
	}

	public void setSKU(String SKU) {
		this.SKU = SKU;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public float getUninWeight() {
		return uninWeight;
	}

	public void setUninWeight(float uninWeight) {
		this.uninWeight = uninWeight;
	}

	public float getShippingWeight() {
		return shippingWeight;
	}

	public void setShippingWeight(float shippingWeight) {
		this.shippingWeight = shippingWeight;
	}

	public int getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(int unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getFlavour() {
		return flavour;
	}

	public void setFlavour(String flavour) {
		this.flavour = flavour;
	}

	public long getUPC() {
		return UPC;
	}

	public void setUPC(long UPC) {
		this.UPC = UPC;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date date) {
		this.expirationDate = date;
	}

	@Override
	public String toString() {
		return "Product [categoryId=" + categoryId + ", supplierId="
				+ supplierId + ", productId=" + productId + ", SKU=" + SKU
				+ ", productName=" + productName + ", productDescription="
				+ productDescription + ", unitPrice=" + unitPrice
				+ ", uninWeight=" + uninWeight + ", shippingWeight="
				+ shippingWeight + ", unitsInStock=" + unitsInStock
				+ ", picture=" + picture + ", flavour=" + flavour + ", UPC="
				+ UPC + ", expirationDate=" + expirationDate + ", \n category="
				+ category + ",\n supplier=" + supplier + "]";
	}
}