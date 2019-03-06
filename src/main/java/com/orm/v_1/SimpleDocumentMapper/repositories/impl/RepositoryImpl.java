package com.orm.v_1.SimpleDocumentMapper.repositories.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.orm.v_1.SimpleDocumentMapper.model.MDocument;
import com.orm.v_1.SimpleDocumentMapper.model.MField;
import com.orm.v_1.SimpleDocumentMapper.model.util.Util;
import com.orm.v_1.SimpleDocumentMapper.odm.config.ConnectionPool;
import com.orm.v_1.SimpleDocumentMapper.repositories.Repository;

public class RepositoryImpl<T> implements Repository<T> {
	
	protected MDocument documentMetadata;
	
	protected ConnectionPool connectionPoolReference;
	
	public RepositoryImpl(MDocument documentMetadata, ConnectionPool connectionPoolReference) {
		this.documentMetadata = documentMetadata;
		this.connectionPoolReference = connectionPoolReference;
	}

	@Override
	public long count() {
		MongoCollection<Document> collectionCursor = connectionPoolReference.provideCollectionCursor(documentMetadata.getCollection());
		return collectionCursor.countDocuments();
	}

	@Override
	public boolean dropCollection() {
		MongoCollection<Document> collectionCursor = connectionPoolReference.provideCollectionCursor(documentMetadata.getCollection());
		collectionCursor.drop();
		return true;
	}
	
	protected Document prepareDocument (T t, MDocument documentMetadata) {
		try {
			Document document = new Document();
			Object value = null;
			for(MField fieldMetadata: documentMetadata.getFields()) {
				if(fieldMetadata.isId()) continue;
				if(fieldMetadata.isEmbedded()) {
					if(fieldMetadata.isList()) {
						List<T> embeddedObjects = (List<T>) fieldMetadata.getRefField().get(t);
						if(Util.isNull(embeddedObjects)) continue;
						List<Document> embeddedDocuments = embeddedObjects.stream().map(embeddedObject -> prepareDocument(embeddedObject, fieldMetadata.getEmbeddedDocumentMetadata())).collect(Collectors.toList());
						document.append(fieldMetadata.getNameInDatabase(), embeddedDocuments);
					} else {
						T embeddedObject = (T) fieldMetadata.getRefField().get(t);
						if(Util.isNull(embeddedObject)) continue;
						Document embeddedDocumentValue = prepareDocument(embeddedObject, fieldMetadata.getEmbeddedDocumentMetadata());
						document.append(fieldMetadata.getNameInDatabase(), embeddedDocumentValue);
					}
					continue;
				}
				if(fieldMetadata.isList()) {
					List<Object> embeddedObjects = (List<Object>) fieldMetadata.getRefField().get(t);
					if(Util.isNull(embeddedObjects)) continue;
					document.append(fieldMetadata.getNameInDatabase(), embeddedObjects);
					continue;
				}
				value = fieldMetadata.getRefField().get(t);
				if(!Util.isNull(value)) {
					if(fieldMetadata.isEnum()) {
						document.append(fieldMetadata.getNameInDatabase(), value.toString());
						continue;
					}
					document.append(fieldMetadata.getNameInDatabase(), value);
				}
			}
			return document;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected T extractDocument(Document document, MDocument documentMedata) {
		try {
			T result = (T) documentMedata.getEntityClass().getConstructor().newInstance();
			for(MField fieldMetadata: documentMedata.getFields()) {
				if(fieldMetadata.isId()) {
					ObjectId objectId = document.getObjectId("_id");
					fieldMetadata.getRefField().set(result, objectId.toString());
					continue;
				}
				if(fieldMetadata.isEmbedded()) {
					if(fieldMetadata.isList()) {
						List<Document> embeddedDocuments = document.getEmbedded(List.of(fieldMetadata.getNameInDatabase()), new ArrayList<Document>());
						if(embeddedDocuments == null || embeddedDocuments.isEmpty()) continue;
						List<T> embeddedResults = new ArrayList<>();
						embeddedDocuments.stream().forEach(embeddedDocument -> embeddedResults.add(extractDocument(embeddedDocument, fieldMetadata.getEmbeddedDocumentMetadata())));
						fieldMetadata.getRefField().set(result, embeddedResults);
					} else {
						Document embeddedDocument = document.getEmbedded(List.of(fieldMetadata.getNameInDatabase()), Document.class);
						if(embeddedDocument == null) continue;
						T embeddedResult = extractDocument(embeddedDocument, fieldMetadata.getEmbeddedDocumentMetadata());
						fieldMetadata.getRefField().set(result, embeddedResult);
					}
					continue;
				} else if(fieldMetadata.isList()) {
					List<Object> values = document.getEmbedded(List.of(fieldMetadata.getNameInDatabase()), new ArrayList<Object>());
					fieldMetadata.getRefField().set(result, values);
					continue;
				} else if(fieldMetadata.isEnum()) {
					String value = document.getString(fieldMetadata.getNameInDatabase());
					if(Util.isNull(value)) continue;
					fieldMetadata.getRefField().set(result, Enum.valueOf((Class)fieldMetadata.getJavaType(), value));
					continue;
				}
				fieldMetadata.getRefField().set(result, fieldMetadata.getJavaType().cast(document.get(fieldMetadata.getNameInDatabase())));
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
