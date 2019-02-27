package com.orm.v_1.SimpleDocumentMapper.model;

import java.util.ArrayList;
import java.util.List;

public class MDocument {
	
	private String collection;
	
	private Class<?> entityClass;
	
	private MDatabase databaseMetadata;
	
	private List<MField> fields;
	
	private MField id;
	
	private boolean embedded;
	
	public MDocument(String collection, Class<?> entityClass, MDatabase databaseMetadata, boolean embedded) {
		this.collection = collection;
		this.entityClass = entityClass;
		this.databaseMetadata = databaseMetadata;
		this.embedded = embedded;
		this.fields = new ArrayList<MField>();
	}
	
	public List<MField> getFields() {
		return fields;
	}
	
	public boolean addField (MField fieldMetadata) {
		if(fieldMetadata == null) return false;
		this.fields.add(fieldMetadata);
		return true;
	}
	
	public String getNameInDatabaseByNameInModel (String nameInModel) {
		for(MField mField: fields) {
			if(mField.getNameInModel().equals(nameInModel)) {
				return mField.getNameInDatabase();
			}
		}
		return null;
	}
	
	public MField getFieldMetadataByNameInModel (String nameInModel) {
		for(MField mField: fields) {
			if(mField.getNameInModel().equals(nameInModel)) {
				return mField;
			}
		}
		return null;
	}
	
	public String getCollection() {
		return collection;
	}
	
	public Class<?> getEntityClass() {
		return entityClass;
	}
	
	public MDatabase getDatabaseMetadata() {
		return databaseMetadata;
	}
	
	public boolean getEmbedded() {
		return this.embedded;
	}
	
	public MField getId() {
		return id;
	}
	
	public void setId(MField id) {
		this.id = id;
	}
	
	public void update() {
		this.fields.stream().forEach(MField::update);
	}

}
