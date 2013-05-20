package org.shop.dao;

import java.sql.SQLException;

public class ConnectionFactoryFactory {
	
	public static enum FactoryType{RAW, C3P0}
	
	private static FactoryType currentType = FactoryType.RAW;
	
	public static synchronized void setType(FactoryType type){
		currentType = type;
	}
	
	public synchronized static ConnectionFactory newConnectionFactory(){
		ConnectionFactory result = null;
		try{
			switch(currentType){
			case RAW:
				result = new ConnectionFactoryJdbc();
				break;
			case C3P0:
				result = new ConnectionFactoryC3P0();
				break;
			default: 
				throw new RuntimeException("default is never call");
			}
		}catch(SQLException e){
			System.out.println(" "+e.getMessage());
		}
		return result;
	}
	
	public static synchronized void close(){
		
	}
	
}
