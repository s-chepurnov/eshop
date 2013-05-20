package org.shop.dao;
import java.sql.PreparedStatement;

public abstract class Preparator<T> {

	abstract void prepare(PreparedStatement ps, T entity) throws DBSystemException;
}
