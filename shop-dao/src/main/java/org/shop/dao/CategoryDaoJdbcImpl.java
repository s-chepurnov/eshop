package org.shop.dao;

import java.util.List;
import org.shop.entity.Category;


public class CategoryDaoJdbcImpl extends AbstractDao<Category> implements CategoryDao{

	public static final String QUERY_SELECT_ALL = "SELECT * FROM shop.category";
	
	public static final String QUERY_SELECT_BY_ID = "SELECT * FROM shop.category WHERE CategoryID=";
	public static final String QUERY_DELETE_BY_ID = "DELETE FROM shop.category WHERE CategoryID=";
	public static final String QUERY_PREPARED_INSERT="INSERT INTO shop.category"				
			+" (CategoryName)"
			+" VALUES (?)";
	
	@Override
	public List<Category> select() throws DBSystemException {
		return selectAll(QUERY_SELECT_ALL, new CategoryExtractor(), Enricher.NOTHING);
	}

	@Override
	public boolean insert(Category entity) throws DBSystemException {
		return insertOne(QUERY_PREPARED_INSERT, entity, new CategoryPreparator());
		
	}

	@Override
	public Category selectById(int id) throws DBSystemException {
		return selectOne(QUERY_SELECT_BY_ID+ id, new CategoryExtractor(), Enricher.NOTHING);
	}

	@Override
	public boolean deleteById(int id) throws DBSystemException {
		return deleteById(QUERY_DELETE_BY_ID, id);
	}
}
