package org.shop.dao;

import java.util.concurrent.Callable;

public class TransactionManagerMock implements TransactionManager{

	@Override
	public <T> T doInTransaction(Callable<T> unitOfWork) throws Exception {

		return unitOfWork.call();
	}

	
}
