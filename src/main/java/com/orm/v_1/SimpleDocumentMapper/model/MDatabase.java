package com.orm.v_1.SimpleDocumentMapper.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MDatabase {
	
	private String name;
	
	private int port;
	
	private Map<Class<?>, MDocument> collections;
	
	public MDatabase(String name, int port) {
		this.name = name;
		this.port = port;
		this.collections = new HashMap<Class<?>, MDocument>();
	}
	
	public boolean putDocumentMetadata (Class<?> entityClass, MDocument documentMetadata) {
		if(entityClass == null || documentMetadata == null) return false;
		this.collections.put(entityClass, documentMetadata);
		return true;
	}
	
	public Optional<MDocument> getDocumentMetadata (Class<?> entityClass) {
		return Optional.ofNullable(collections.get(entityClass));
	}
	
	public String getName() {
		return name;
	}
	
	public int getPort() {
		return port;
	}
	
	public void update() {
		this.collections.values().stream().forEach(MDocument::update);
	}

}
