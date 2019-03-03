package com.orm.v_1.SimpleDocumentMapper.odm.config.impl;

import java.util.List;
import java.util.Optional;

import com.orm.v_1.SimpleDocumentMapper.model.MDatabase;
import com.orm.v_1.SimpleDocumentMapper.model.MDocument;
import com.orm.v_1.SimpleDocumentMapper.model.exceptions.NotSupportEntity;
import com.orm.v_1.SimpleDocumentMapper.odm.config.ConnectionPool;
import com.orm.v_1.SimpleDocumentMapper.odm.config.IndigoConfigurator;
import com.orm.v_1.SimpleDocumentMapper.odm.scanner.ModelMapperScanner;
import com.orm.v_1.SimpleDocumentMapper.odm.scanner.impl.ModelMapperScannerImpl;
import com.orm.v_1.SimpleDocumentMapper.proxy.ProxyRepository;
import com.orm.v_1.SimpleDocumentMapper.repositories.CrudRepository;
import com.orm.v_1.SimpleDocumentMapper.repositories.PagingAndSortingRepository;
import com.orm.v_1.SimpleDocumentMapper.repositories.SpecificationRepository;
import com.orm.v_1.SimpleDocumentMapper.repositories.impl.CrudRepositoryImpl;
import com.orm.v_1.SimpleDocumentMapper.repositories.impl.PagingAndSortingRepositoryImpl;
import com.orm.v_1.SimpleDocumentMapper.repositories.impl.SpecificationRepositoryImpl;

public class IndigoConfiguratorImpl implements IndigoConfigurator {
	
	private ConnectionPool connectionPool;
	
	private MDatabase mDatabase;
	
	public IndigoConfiguratorImpl(String host, int port, String database, List<Class<?>> entities) {
		this.connectionPool = new ConnectionPoolImpl(database, host, port);
		ModelMapperScanner modelMapperScanner = new ModelMapperScannerImpl();
		this.mDatabase = modelMapperScanner.scanEntities(database, port, entities);
	}

	@Override
	public MDatabase provideModel() {
		return this.mDatabase;
	}

	@Override
	public <T> CrudRepository<T> provideCrudRepository(Class<?> entity) {
		Optional<MDocument> optionalDocumentMetadata = mDatabase.getDocumentMetadata(entity);
		if(optionalDocumentMetadata.isPresent()) {
			MDocument document = optionalDocumentMetadata.get();
			return new CrudRepositoryImpl<>(document, connectionPool);
		}
		throw new NotSupportEntity("This isn't entity class!");
	}

	@Override
	public <T> SpecificationRepository<T> provideSpecificationRepository(Class<?> entity) {
		Optional<MDocument> optionalDocumentMetadata = mDatabase.getDocumentMetadata(entity);
		if(optionalDocumentMetadata.isPresent()) {
			MDocument document = optionalDocumentMetadata.get();
			return new SpecificationRepositoryImpl<>(document, connectionPool);
		}
		throw new NotSupportEntity("This isn't entity class!");
	}

	@Override
	public <T> PagingAndSortingRepository<T> providePagingAndSortingRepository(Class<?> entity) {
		Optional<MDocument> optionalDocumentMetadata = mDatabase.getDocumentMetadata(entity);
		if(optionalDocumentMetadata.isPresent()) {
			MDocument document = optionalDocumentMetadata.get();
			return new PagingAndSortingRepositoryImpl<>(document, connectionPool);
		}
		throw new NotSupportEntity("This isn't entity class!");
	}

	@Override
	public <T> T provideProxy(Class<?> repositoryClass, Class<T> entityClass) {
		Optional<MDocument> optionalDocumentMetadata = mDatabase.getDocumentMetadata(entityClass);
		if(!optionalDocumentMetadata.isPresent()) {
			// TODO: Exception
		}
		MDocument documentMetadata = optionalDocumentMetadata.get();
		return (T) ProxyRepository.newInstance(new Class[]{ repositoryClass }, documentMetadata, connectionPool);
	}

}
