package org.shop.dao;

import java.util.List;
import org.shop.entity.Product;


public class ProductDaoJdbcImpl extends AbstractDao<Product> implements ProductDao {
	private final SupplierDao supplierDao = new SupplierDaoJdbcImpl();
	private final CategoryDao categoryDao = new CategoryDaoJdbcImpl();

	public static final String QUERY_SELECT_ALL = "SELECT * FROM shop.products";
	public static final String QUERY_SELECT_PAGE = "SELECT * FROM shop.products ORDER BY ProductID ASC LIMIT ";
	public static final String QUERY_SELECT_COUNT = "SELECT COUNT(*) FROM shop.products USE INDEX(PRIMARY)";
	public static final String QUERY_SELECT_BY_ID = "SELECT * FROM shop.products WHERE ProductID= ";
	public static final String QUERY_DELETE_BY_ID = "DELETE FROM shop.products WHERE ProductID = ";
	public static final String QUERY_PREPARED_INSERT="INSERT INTO Products"				
			+" (SKU,ProductName,ProductDescription,UnitPrice,UnitWeight,UnitsInStock,Picture,CategoryID,SupplierID,Flavour,UPC,ShippingWeight,ExpirationDate)"
			+" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	@Override
	public List<Product> select() throws DBSystemException {
		
		List<Product> products = selectAll(QUERY_SELECT_ALL, new ProductExtractor(), new ProductEnricher(supplierDao, categoryDao));
		return products;
	}
	@Override
	public int selectCount() throws DBSystemException {
		return selectOneRow(QUERY_SELECT_COUNT);
	}
	
	@Override
	public List<Product> selectPagination(int from, int count) throws DBSystemException {
		List<Product> products = selectAll(QUERY_SELECT_PAGE + from+","+count, new ProductExtractor(), new ProductEnricher(supplierDao, categoryDao));
		return products;
	}
	@Override
	public Product selectById(int id) throws DBSystemException{
		return selectOne(QUERY_SELECT_BY_ID+ id, new ProductExtractor(), /*Enricher.NOTHING*/new ProductEnricher(supplierDao, categoryDao));
	}
	
	@Override
	public boolean insert(Product product) throws DBSystemException {
		return insertOne(QUERY_PREPARED_INSERT, product, new ProductPreparator());
	}
	
	@Override
	public boolean deleteById(int id) throws DBSystemException{
		return deleteById(QUERY_DELETE_BY_ID, id);
	}
}