package org.shop.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.shop.entity.Customer;

public class CustomerExtractor extends Extractor<Customer>{

	@Override
	public Customer extractOne(ResultSet rs) throws SQLException {
		
		int id = rs.getInt("CustomerID");
		String firstName= rs.getString("FirstName");
		String lastName= rs.getString("LastName");
		String email = rs.getString("Email");
		String pswd = rs.getString("Password");
		Timestamp registrationDate = rs.getTimestamp("RegistrationDate");
		String customerCellPhone = rs.getString("CustomerCellPhone");
		int addressId = rs.getInt("AddressID");
		
		Customer customer = new Customer(firstName, lastName, email, pswd);
		customer.setCustomerId(id);
		customer.setRegistrationDate(registrationDate);
		customer.setCustomerCellPhone(customerCellPhone);
		customer.setAddressId(addressId);
		
		return customer;
	}
}
