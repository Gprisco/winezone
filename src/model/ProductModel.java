package model;

import java.sql.SQLException;
import java.util.Collection;

public interface ProductModel<T, PK> {
	public T findByPk(PK pk) throws SQLException;

	public Collection<T> findAll(int limit, int offset) throws SQLException;

	public void create(T product) throws SQLException;

	public int update(T product) throws SQLException;

	public int destroy(PK pk) throws SQLException;
}
