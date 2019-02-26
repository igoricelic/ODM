package com.orm.v_1.SimpleDocumentMapper.repositories;

public interface Repository<T> {
	
	public long count();
	
	public boolean dropCollection();

}
