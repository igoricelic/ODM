package com.orm.v_1.SimpleDocumentMapper.odm.config;

import com.orm.v_1.SimpleDocumentMapper.model.MDatabase;
import com.orm.v_1.SimpleDocumentMapper.repositories.CrudRepository;

public interface IndigoConfigurator {
	
	public MDatabase provideModel();
	
	public <T> CrudRepository<T> provideCrudRepository(Class<?> entity);

}
