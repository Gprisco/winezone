package db;

import java.util.Collection;

public interface ProductModel<T, PK> {
	public T findByPk(PK pk);

	public Collection<T> findAll(int limit, int offset);

	public void create(T product);

	public int update(T product);

	public int destroy(PK pk);
}
