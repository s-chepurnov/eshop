package org.shop.dao;
import java.util.List;

import org.shop.entity.Customer;


public class CustomerDaoJdbcImpl extends AbstractDao<Customer> implements CustomerDao{
	
	public static final String QUERY_SELECT_ALL = "SELECT * FROM shop.Customers";
	public static final String QUERY_SELECT_BY_ID = "SELECT * FROM shop.Customers WHERE CustomersID=";
	public static final String QUERY_SELECT_BY_EMAIL = "SELECT * FROM shop.Customers WHERE Email= ";
	public static final String QUERY_DELETE_BY_ID = "DELETE FROM shop.Customers WHERE CustomersID=";

	public static final String QUERY_PREPARED_INSERT="INSERT INTO shop.Customers"				
			+" (FirstName,LastName,Email,Password,RegistrationDate,CustomerCellPhone,AddressID)"
			+" VALUES (?,?,?,?,?,?,?)";
	
	@Override
	public List<Customer> select() throws DBSystemException {
		return selectAll(QUERY_SELECT_ALL, new CustomerExtractor(), Enricher.NOTHING);
	}

	@Override
	public boolean insert(Customer entity) throws DBSystemException {
		return insertOne(QUERY_PREPARED_INSERT, entity, new CustomerPreparator());
	}

	@Override
	public Customer selectById(int id) throws DBSystemException {
		return selectOne(QUERY_SELECT_BY_ID + id, new CustomerExtractor(), Enricher.NOTHING);
	}
	@Override
	public Customer selectByEmail(String email) throws DBSystemException {
		return selectOne(QUERY_SELECT_BY_EMAIL +"'"+email+"'", new CustomerExtractor(), Enricher.NOTHING);
	}

	@Override
	public boolean deleteById(int id) throws DBSystemException {
		return deleteById(QUERY_DELETE_BY_ID, id);
	}
}
