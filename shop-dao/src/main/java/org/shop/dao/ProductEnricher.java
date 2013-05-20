package org.shop.dao;

import org.shop.entity.Category;
import org.shop.entity.Product;
import org.shop.entity.Supplier;

public class ProductEnricher implements Enricher<Product>{
	
	private final SupplierDao supplierDao;
	private final CategoryDao categoryDao;
	
	public ProductEnricher(SupplierDao supplierDao, CategoryDao categoryDao) {
		this.supplierDao = supplierDao;
		this.categoryDao = categoryDao;
	}
	
	@Override
	public void enrich(Product record) throws DBSystemException{
		Category category = categoryDao.selectById(record.getCategoryId());
		Supplier supplier = supplierDao.selectById(record.getSupplierId());
		record.setCategory(category);
		record.setSupplier(supplier);
	}
}