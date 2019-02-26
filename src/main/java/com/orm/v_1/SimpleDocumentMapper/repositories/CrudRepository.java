package com.orm.v_1.SimpleDocumentMapper.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> extends Repository<T> {
	
	public List<T> readAll ();
	
	public Optional<T> readById (Object id);
	
	public T createOne (T t);
	
	public Iterable<T> createMore (Iterable<T> objects);
	
	public T updateOne (T t);
	
	public Iterable<T> updateMore (Iterable<T> objects);
	
	public T deleteOne (T t);
	
	public Iterable<T> deleteMore (Iterable<T> objects);
	
	public boolean deleteAll ();

}
