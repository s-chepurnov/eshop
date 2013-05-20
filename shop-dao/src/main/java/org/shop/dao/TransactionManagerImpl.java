package org.shop.dao;

import java.sql.Connection;
import java.util.concurrent.Callable;
import org.apache.log4j.Logger;
import org.shop.utils.ClassNameUtil;

public class TransactionManagerImpl implements TransactionManager{
	public static ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();
	public static final ConnectionFactory factory = ConnectionFactoryFactory.newConnectionFactory();
	
	private static final Logger logger = Logger.getLogger(ClassNameUtil.getCurrentClassName());
	@Override
	public <T> T doInTransaction(Callable<T> unitOfWork) throws Exception {
		logger.debug("begin TransactionManager.doInTransaction()");
		Connection connection = factory.getConnection();
		logger.debug("factory connection=" + connection + "Thread="+ Thread.currentThread());
		connectionHolder.set(connection);
		try{
			T result = unitOfWork.call();
			logger.debug("result unitOfWork.call() "+result);
			logger.debug("try to commit");
			connection.commit();
			logger.debug("trnsctn commited successfully");
			return result;
		}catch(Exception e){
			logger.warn("Exception occure while doing transaction",e); 
			connection.rollback();
			logger.warn("rollback");
			throw e; 
		}finally{
			logger.debug("close connection");
			JdbcUtils.closeQuietly(connection);
			logger.debug("unlink connection from thread"+ connection);
			connectionHolder.remove();
		}
	}
	
	public static Connection getConnection(){
		return connectionHolder.get();
	}
}
