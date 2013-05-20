package org.shop.dao;
public class DBException extends Exception{
	private static final long serialVersionUID = 1L;

	public DBException(String message, Throwable cause) {
		super(message, cause);
	}

}
