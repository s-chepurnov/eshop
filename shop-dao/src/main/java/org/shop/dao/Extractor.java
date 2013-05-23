package org.shop.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Extractor<T> {
	
	public abstract T extractOne(ResultSet rs) throws SQLException;
	
	public List<T> extractAll(ResultSet rs) throws SQLException{
		List<T> result = new ArrayList<>();
		
		while(rs.next()){
			result.add(extractOne(rs));
		}
		return result;
	}
}