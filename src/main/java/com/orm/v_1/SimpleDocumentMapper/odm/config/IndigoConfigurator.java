package com.orm.v_1.SimpleDocumentMapper.odm.config;

import com.orm.v_1.SimpleDocumentMapper.repositories.CrudRepository;
import com.orm.v_1.SimpleDocumentMapper.repositories.PagingAndSortingRepository;
import com.orm.v_1.SimpleDocumentMapper.repositories.Repository;
import com.orm.v_1.SimpleDocumentMapper.repositories.SpecificationRepository;

public interface IndigoConfigurator {
	
	public <T> Repository<T> provideRepository (Class<?> entity);
	
	public <T> CrudRepository<T> provideCrudRepository(Class<?> entity);
	
	public <T> SpecificationRepository<T> provideSpecificationRepository(Class<?> entity);
	
	public <T> PagingAndSortingRepository<T> providePagingAndSortingRepository(Class<?> entity);
	
	public <T> T provideProxy (Class<?> repositoryClass);

}
