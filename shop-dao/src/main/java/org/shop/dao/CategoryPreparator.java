package org.shop.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.shop.entity.Category;

public class CategoryPreparator extends Preparator<Category>{

	@Override
	void prepare(PreparedStatement ps, Category entity) throws DBSystemException {
		try {
			ps.setString(1,entity.getCategoryName());
		} catch (SQLException e) {
			throw new DBSystemException("CategoryPreparator" + e.getMessage(), e);
		}
	}

}
