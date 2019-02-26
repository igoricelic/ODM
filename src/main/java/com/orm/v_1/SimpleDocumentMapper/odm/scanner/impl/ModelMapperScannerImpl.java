package com.orm.v_1.SimpleDocumentMapper.odm.scanner.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import com.orm.v_1.SimpleDocumentMapper.model.MDatabase;
import com.orm.v_1.SimpleDocumentMapper.model.MDocument;
import com.orm.v_1.SimpleDocumentMapper.model.MField;
import com.orm.v_1.SimpleDocumentMapper.model.annotations.Document;
import com.orm.v_1.SimpleDocumentMapper.model.annotations.Embedded;
import com.orm.v_1.SimpleDocumentMapper.model.annotations.Id;
import com.orm.v_1.SimpleDocumentMapper.model.annotations.Transient;
import com.orm.v_1.SimpleDocumentMapper.model.util.Constants;
import com.orm.v_1.SimpleDocumentMapper.model.util.Util;
import com.orm.v_1.SimpleDocumentMapper.odm.scanner.ModelMapperScanner;

public class ModelMapperScannerImpl implements ModelMapperScanner {

	@Override
	public MDatabase scanEntities(String dbName, int port, List<Class<?>> entities) {
		MDatabase mDatabase = new MDatabase(dbName, port);
		entities.stream().forEach(entity -> mDatabase.putDocumentMetadata(entity, scanEntity(entity, mDatabase)));
		mDatabase.update();
		return mDatabase;
	}
	
	private MDocument scanEntity (Class<?> entity, MDatabase database) {
		String collection = entity.getSimpleName();
		boolean embedded = false;
		Document documentAnnotation = entity.getDeclaredAnnotation(Document.class);
		if(!Util.isNull(documentAnnotation)) {
			if(!documentAnnotation.collection().equals(Constants.EMPTY_WORD)) collection = documentAnnotation.collection();
			embedded = documentAnnotation.embedded();
		}
		MDocument mDocument = new MDocument(collection, entity, database, embedded);
		for(Field field: entity.getDeclaredFields()) {
			if(Util.isNull(field.getDeclaredAnnotation(Transient.class))) {
				mDocument.addField(scanField(field, mDocument));
			}
		}
		return mDocument;
	}
	
	private MField scanField (Field field, MDocument document) {
		MField mField = null;
		com.orm.v_1.SimpleDocumentMapper.model.annotations.Field fieldAnnotaion = field.getAnnotation(com.orm.v_1.SimpleDocumentMapper.model.annotations.Field.class);
		Embedded embeddedFieldAnnotaion = field.getDeclaredAnnotation(Embedded.class);
		if(!Util.isNull(fieldAnnotaion) || !Util.isNull(embeddedFieldAnnotaion)) {
			String name = (!Util.isNull(fieldAnnotaion)) ? fieldAnnotaion.name() : embeddedFieldAnnotaion.name();
			mField = new MField(field, document, name);
			if(!Util.isNull(embeddedFieldAnnotaion)) {
				mField.setEmbedded(true);
			}
		} else {
			mField = new MField(field, document);
		}
		if(!Util.isNull(field.getDeclaredAnnotation(Id.class))) mField.setId(true);
		Class<?> javaType = checkIsParameterizedList(field);
		if(!Util.isNull(javaType)) {
			mField.setList(true);
			mField.setJavaType(javaType);
		}
		return mField;
	}
	
	private Class<?> checkIsParameterizedList(Field field) {
		Type type = field.getGenericType();
		if(type instanceof ParameterizedType) {
			ParameterizedType paramType = (ParameterizedType) type;
            Type[] argTypes = paramType.getActualTypeArguments();
            return (Class<?>) argTypes[0];
		}
		return null;
	}

}
