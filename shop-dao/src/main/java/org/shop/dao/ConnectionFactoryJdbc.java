package org.shop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryJdbc implements ConnectionFactory{
	public static final String DRIVER_CLASS_NAME ="com.mysql.jdbc.Driver";
	public static final String USER ="root";
	public static final String PASSWORD = "root";
	public static final String URL = "jdbc:mysql://127.0.0.1:3306/shop";

	@Override
	public Connection getConnection() throws SQLException{
		JdbcUtils.initDriver(DRIVER_CLASS_NAME);
		Connection connection=null;
		connection = DriverManager.getConnection(URL, USER, PASSWORD);
		return connection;
	}
}