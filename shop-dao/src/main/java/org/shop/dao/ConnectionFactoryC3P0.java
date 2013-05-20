package org.shop.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactoryC3P0 implements ConnectionFactory{
	
	private final ComboPooledDataSource dataSource;
	
	public static final String DRIVER_CLASS_NAME ="com.mysql.jdbc.Driver";
	public static final String USER ="root";
	public static final String PASSWORD = "root";
	public static final String URL = "jdbc:mysql://127.0.0.1:3306/shop";
	
	public ConnectionFactoryC3P0() throws SQLException{
		try{
			dataSource = new ComboPooledDataSource();
			dataSource.setDriverClass(DRIVER_CLASS_NAME); 
			dataSource.setJdbcUrl(URL);
			dataSource.setUser(USER);
			dataSource.setPassword(PASSWORD);
		}catch(PropertyVetoException e){
			throw new RuntimeException("Exception during configuring C3P0", e);
		}
	}

	@Override
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

}
