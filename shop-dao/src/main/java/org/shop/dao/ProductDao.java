package org.shop.dao;
import java.util.List;

import org.shop.entity.Product;

public interface ProductDao extends Dao<Product>{
	
	public boolean deleteById(int id) throws DBSystemException;
	public List<Product> select() throws DBSystemException;
	public List<Product> selectPagination(int from, int count) throws DBSystemException;
	public boolean insert(Product product) throws DBSystemException;
	public Product selectById(int id) throws DBSystemException;	
}