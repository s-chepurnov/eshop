package org.shop.dao;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.shop.entity.Product;

public class ProductPreparator extends Preparator<Product>{
	void prepare(PreparedStatement ps, Product entity) throws DBSystemException{
		try {
			ps.setString(1, entity.getSKU());
			ps.setString(2, entity.getProductName());
			ps.setString(3, entity.getProductDescription());
			ps.setBigDecimal(4, entity.getUnitPrice());
			ps.setFloat(5, entity.getUninWeight());
			ps.setInt(6, entity.getUnitsInStock());
			ps.setString(7, entity.getPicture());
			ps.setInt(8, entity.getCategoryId());
			ps.setInt(9, entity.getSupplierId());
			ps.setString(10, entity.getFlavour());
			ps.setLong(11, entity.getUPC());
			ps.setFloat(12, entity.getShippingWeight());
			ps.setDate(13, entity.getExpirationDate());
		} catch (SQLException e) {
			throw new DBSystemException("can't prepare " + e.getMessage(), e);
		}
	}
}