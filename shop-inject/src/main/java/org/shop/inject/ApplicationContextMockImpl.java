package org.shop.inject;

import org.shop.dao.ProductDaoJdbcImpl;
import org.shop.dao.TransactionManagerImpl;

public class ApplicationContextMockImpl implements ApplicationContext{
	private String xmlFile;
	
	@Override
	public void init(String xmlFile) {
		this.xmlFile = xmlFile;
	}

	@Override
	public Object getBean(String name) {
		return getBean(name, Object.class);
	}

	@Override
	public <T> T getBean(String name, Class<T> clazz) {
		if("productDao".equals(name)){
			return (T) new ProductDaoJdbcImpl();
		}else if("trManager".equals(name)){
			return (T) new TransactionManagerImpl();
		}else{
			throw new IllegalArgumentException("No such entity for this name: " + name);
		}
	}
}