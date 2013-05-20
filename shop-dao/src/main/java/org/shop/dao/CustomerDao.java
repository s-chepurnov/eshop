package org.shop.dao;

import java.util.List;

import org.shop.entity.Customer;

public interface CustomerDao extends Dao<Customer>{

	public List<Customer> select() throws DBSystemException;
	public boolean insert(Customer entity) throws DBSystemException;
	public Customer selectById(int id) throws DBSystemException;
	public Customer selectByEmail(String email) throws DBSystemException;
	public boolean deleteById(int id) throws DBSystemException;
}
