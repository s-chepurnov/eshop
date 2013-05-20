package org.shop.dao;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.shop.entity.Customer;

public class CustomerPreparator extends Preparator<Customer>{

	@Override
	void prepare(PreparedStatement ps, Customer entity) throws DBSystemException {
		try {
			ps.setString(1, entity.getFirstName());
			ps.setString(2, entity.getLastName());
			ps.setString(3, entity.getEmail());
			ps.setString(4, entity.getPassword());
			ps.setTimestamp(5, entity.getRegistrationDate());
			ps.setString(6, entity.getCustomerCellPhone());
			
			//this is the possibility to add new customer without 'address'
			int addressId = entity.getAddressId();
			if(addressId==0){
				ps.setNull(7, java.sql.Types.INTEGER);
			}else{
				ps.setInt(7, addressId); 
			}
			
		} catch (SQLException e) {
			throw new DBSystemException("CustomerPreparator"+ e.getMessage(), e);
		}
	}
}
