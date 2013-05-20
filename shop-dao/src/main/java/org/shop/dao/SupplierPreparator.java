package org.shop.dao;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.shop.entity.Supplier;

public class SupplierPreparator extends Preparator<Supplier> {

	@Override
	void prepare(PreparedStatement ps, Supplier entity) throws DBSystemException {
		try {
			ps.setString(1, entity.getCompanyName());
			ps.setString(2, entity.getAddress());
			ps.setString(3, entity.getUrl());
			ps.setString(4, entity.getContacts());
			ps.setString(4, entity.getNotes());

		} catch (SQLException e) {
			throw new DBSystemException("SupplierPreparator " + e.getMessage(), e);
		}
	}

}
