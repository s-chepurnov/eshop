package org.shop.dao;

import java.util.List;
import org.shop.entity.Supplier;

public class SupplierDaoJdbcImpl extends AbstractDao<Supplier> implements SupplierDao {

	public static final String QUERY_SELECT_ALL = "SELECT * FROM shop.Suppliers";
	public static final String QUERY_SELECT_BY_ID = "SELECT * FROM shop.Suppliers WHERE SupplierID=";
	public static final String QUERY_DELETE_BY_ID = "DELETE FROM shop.Suppliers WHERE SupplierID=";
	public static final String QUERY_PREPARED_INSERT="INSERT INTO shop.Suppliers"				
			+" (CompanyName, Address, URL, Contacts, Notes)"
			+" VALUES (?,?,?,?,?)";
	
	@Override
	public List<Supplier> select() throws DBSystemException {
		return selectAll(QUERY_SELECT_ALL, new SupplierExtractor(), Enricher.NOTHING);
	}

	@Override
	public boolean insert(Supplier entity) throws DBSystemException {
		return insertOne(QUERY_PREPARED_INSERT, entity, new SupplierPreparator());
	}

	@Override
	public Supplier selectById(int id) throws DBSystemException {
		return selectOne(QUERY_SELECT_BY_ID+id, new SupplierExtractor(), Enricher.NOTHING);
	}

	@Override
	public boolean deleteById(int id) throws DBSystemException {
		return deleteById(QUERY_DELETE_BY_ID, id);
	}
}