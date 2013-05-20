package org.shop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.shop.entity.Supplier;

public class SupplierExtractor  extends Extractor<Supplier>{

	@Override
	public Supplier extractOne(ResultSet rs) throws SQLException {
		
		int id = rs.getInt("SupplierID");
		String name = rs.getString("CompanyName");
		String address = rs.getString("Address");
		String URL = rs.getString("URL");
		String contacts = rs.getString("Contacts");
		String notes = rs.getString("Notes");
		
		Supplier supplier = new Supplier(name);
		supplier.setId(id);
		supplier.setAddress(address);
		supplier.setUrl(URL);
		supplier.setContacts(contacts);
		supplier.setNotes(notes);
		
		return supplier;
	}

}
