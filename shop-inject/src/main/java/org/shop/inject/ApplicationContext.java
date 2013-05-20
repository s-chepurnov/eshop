package org.shop.inject;

public interface ApplicationContext {

	public void init(String xmlFile);
	public Object getBean(String name);
	public <T> T getBean(String name, Class<T> clazz);
}
