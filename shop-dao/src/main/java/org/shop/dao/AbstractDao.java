package org.shop.dao;

import org.shop.utils.ClassNameUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public abstract class AbstractDao<T> {
	
	private static final Logger logger = Logger.getLogger(ClassNameUtil.getCurrentClassName());
	
	protected Connection getConnection(){
		Connection result = TransactionManagerImpl.getConnection();
		if(result == null){
			throw new IllegalStateException("getConnection == null");
		}
		return result;
	}

	protected Connection getSerializableConnection() throws DBSystemException{
		try{
			Connection result = getConnection();
			result.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			result.setAutoCommit(false);
			return result;
		}catch(Exception e){
			throw new DBSystemException("can't open connection " + e.getMessage(), e);
		}
	}
	protected List<T> selectAll(String sql, Extractor<T> extractor, Enricher<T> enricher) throws DBSystemException {
		logger.debug("call getSerializableConnection()");
		Connection connection = getSerializableConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		try{
			logger.debug("createStatement()");
			statement = connection.createStatement();
			logger.debug("executeQuery(sql)" + sql);
			resultSet = statement.executeQuery(sql);

			List<T> result = new ArrayList<>();

			while (resultSet.next()){ 
				T record = extractor.extractOne(resultSet);
				logger.debug("extract: " + record);
				enricher.enrich(record);
				logger.debug("enrich:" + record);
				result.add(record);
			}
			return result;
		}catch(SQLException e){
			logger.warn("exception occur while extracting data", e);
			throw new DBSystemException("can't execute SQL query " + sql+ " " + e.getMessage(), e);
		}
		finally{
			logger.debug("try to close resultSet");
			JdbcUtils.closeQuietly(resultSet);
			logger.debug("try to close statement");
			JdbcUtils.closeQuietly(statement);
		}
	}
	protected T selectOne(String sql, Extractor<T> extractor, Enricher<T> enricher) throws DBSystemException {

		Connection connection = getSerializableConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		T result = null;
		try{
			logger.debug("createStatement()");
			statement = connection.createStatement();
			logger.debug("executeQuery(sql)" + sql);
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()){ 
				result = extractor.extractOne(resultSet);
				logger.debug("extract: " + result);
				enricher.enrich(result);
				logger.debug("enrich: " + result);
			}
			return result;
		}catch(SQLException e){
			logger.warn("exception occur while extracting data", e);
			throw new DBSystemException("can't selectById " + sql + "\n" + e.getMessage(), e);
		}
		finally{
			logger.debug("try to close resultSet");
			JdbcUtils.closeQuietly(resultSet);
			logger.debug("try to close statement");
			JdbcUtils.closeQuietly(statement);
		}
	}

	protected boolean insertOne(String sqlPrepared, T entity, Preparator<T> pr) throws DBSystemException {
		boolean fine = false;
		Connection connection = getSerializableConnection();
		PreparedStatement ps = null;
		int result;
		try{
			logger.debug("call preparedStatement()");
			ps = connection.prepareStatement(sqlPrepared);
			pr.prepare(ps, entity);
			logger.debug("call executeUpdate()");
			result = ps.executeUpdate();
			logger.debug("result of executeUpdate(): " + result);
			if(result > 0 ) {
				fine = true;
			}
			return fine;
		}catch(SQLException e){
			logger.warn("exception occur while inserting data", e);
			throw new DBSystemException("can't execute INSERT " + sqlPrepared + "\n" + e.getMessage(), e);
		}
		finally{
			logger.debug("try to close PreparedStatement");
			JdbcUtils.closeQuietly(ps);
		}
	}
	protected boolean deleteById(String sql, int id) throws DBSystemException {
		boolean fine = false;
		Connection connection = getSerializableConnection();
		PreparedStatement ps = null;
		int result;
		try{
			logger.debug("call preparedStatement()");
			ps = connection.prepareStatement(sql+id);
			logger.debug("call executeUpdate()");
			result = ps.executeUpdate();
			logger.debug("result of executeUpdate()" + result);
			if(result > 0 ) {
				fine = true;
			}
			return fine;
		}catch(SQLException e){
			logger.warn("exception occur while delete data", e);
			throw new DBSystemException("can't execute DELETE " + sql + "\n" + e.getMessage(), e);
		}
		finally{
			logger.debug("try to close statement");
			JdbcUtils.closeQuietly(ps);
		}
	}
}