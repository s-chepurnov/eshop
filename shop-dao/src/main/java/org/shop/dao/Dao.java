package org.shop.dao;
import java.util.List;

public interface Dao<T> {
	public List<T> select() throws DBSystemException;
	public boolean insert(T entity) throws DBSystemException;
	public T selectById(int id) throws DBSystemException;
	public boolean deleteById(int id) throws DBSystemException;
}
