package com.orm.v_1.SimpleDocumentMapper.model;

import java.lang.reflect.Field;
import java.util.Optional;

import com.orm.v_1.SimpleDocumentMapper.model.exceptions.NotSupportEntity;
import com.orm.v_1.SimpleDocumentMapper.model.util.Constants;

public class MField {
	
	private String nameInModel, nameInDatabase;
	
	private Class<?> javaType;
	
	private Field refField;
	
	private MDocument documentMetadata;
	
	private boolean id;
	
	/**
	 * If document is embedded
	 */
	private boolean embedded;
	
	private boolean isList, isEnum;
	
	private MDocument embeddedDocumentMetadata;
	
	public MField(Field field, MDocument documentMetadata, String nameInDatabase) {
		field.setAccessible(true);
		this.nameInModel = field.getName();
		this.javaType = field.getType();
		if(this.javaType.isEnum()) this.isEnum = true;
		this.refField = field;
		this.documentMetadata = documentMetadata;
		this.nameInDatabase = nameInDatabase;
		this.id = false;
	}
	
	public MField(Field field, MDocument documentMetadata) {
		this(field, documentMetadata, field.getName());
	}
	
	public MDocument getEmbeddedDocumentMetadata() {
		return embeddedDocumentMetadata;
	}
	
	public void setDocumentMetadata(MDocument documentMetadata) {
		this.documentMetadata = documentMetadata;
	}
	
	public boolean isEmbedded() {
		return embedded;
	}
	
	public void setEmbedded(boolean embedded) {
		this.embedded = embedded;
	}

	public String getNameInModel() {
		return nameInModel;
	}

	public String getNameInDatabase() {
		return nameInDatabase;
	}

	public Class<?> getJavaType() {
		return javaType;
	}
	
	public void setJavaType(Class<?> javaType) {
		this.javaType = javaType;
	}

	public Field getRefField() {
		return refField;
	}

	public MDocument getDocumentMetadata() {
		return documentMetadata;
	}
	
	public boolean isId() {
		return id;
	}
	
	public void setId(boolean fieldIsId) {
		this.id = fieldIsId;
		this.documentMetadata.setId(this);
	}
	
	public boolean isList() {
		return isList;
	}
	
	public void setList(boolean list) {
		this.isList = list;
	}
	
	public boolean isEnum() {
		return isEnum;
	}
	
	public void setEnum(boolean isEnum) {
		this.isEnum = isEnum;
	}
	
	public void update() {
		if(this.nameInDatabase.equals(Constants.EMPTY_WORD)) {
			this.nameInDatabase = this.nameInModel;
		}
		if(id) {
			this.nameInDatabase = "_id";
		}
		if(embedded) {
			Optional<MDocument> optionalEmveddedDocument = this.documentMetadata.getDatabaseMetadata().getDocumentMetadata(javaType);
			if(optionalEmveddedDocument.isPresent() && optionalEmveddedDocument.get().getEmbedded()) {
				this.embeddedDocumentMetadata = optionalEmveddedDocument.get();
			} else {
				System.out.println(javaType);
				throw new NotSupportEntity("Not suport embedded entity!");
			}
		}
	}

}
