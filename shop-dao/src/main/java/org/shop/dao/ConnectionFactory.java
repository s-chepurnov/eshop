package org.shop.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionFactory {
	
	public Connection getConnection() throws SQLException;

}
