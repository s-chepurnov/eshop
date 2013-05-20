package org.shop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.shop.entity.Category;

public class CategoryExtractor extends Extractor<Category>{

	@Override
	public Category extractOne(ResultSet rs) throws SQLException {
		int id = rs.getInt("CategoryID");
		String name = rs.getString("CategoryName");
		Category result= new Category(name);
		result.setCategoryId(id);
		return result;
	}

	
}
