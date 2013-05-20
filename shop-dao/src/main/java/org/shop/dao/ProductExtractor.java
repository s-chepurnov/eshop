package org.shop.dao;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.shop.entity.Product;

public class ProductExtractor extends Extractor<Product>{

	public Product extractOne(ResultSet rs) throws SQLException{
		
		int productId = rs.getInt("ProductID");
		String SKU = rs.getString("SKU");
		String productName = rs.getString("ProductName");
		String productDescription = rs.getString("ProductDescription");
		BigDecimal unitPrice = rs.getBigDecimal("UnitPrice");
		float unitWeight = rs.getFloat("UnitWeight");
		int unitsInStock=rs.getInt("UnitsInStock");
		String picture=rs.getString("picture");
		int category = rs.getInt("CategoryID");
		int supplier = rs.getInt("SupplierID");
		String flavour=rs.getString("Flavour");
		long UPC=rs.getLong("UPC");
		float shippingWeight=rs.getFloat("ShippingWeight");
		Date expirationDate=rs.getDate("ExpirationDate");
		
		
		final Product product = new Product(category, supplier, SKU, productName, unitPrice, unitWeight,
				shippingWeight, unitsInStock, flavour, UPC, expirationDate);
		product.setProductId(productId);
		product.setProductDescription(productDescription);
		product.setPicture(picture);
		
		return product;
	}
}