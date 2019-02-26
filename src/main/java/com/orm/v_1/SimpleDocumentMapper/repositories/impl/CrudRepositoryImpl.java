package com.orm.v_1.SimpleDocumentMapper.repositories.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.orm.v_1.SimpleDocumentMapper.model.MDocument;
import com.orm.v_1.SimpleDocumentMapper.model.MField;
import com.orm.v_1.SimpleDocumentMapper.model.util.Util;
import com.orm.v_1.SimpleDocumentMapper.odm.config.ConnectionPool;
import com.orm.v_1.SimpleDocumentMapper.repositories.CrudRepository;

public class CrudRepositoryImpl<T> extends RepositoryImpl<T> implements CrudRepository<T> {

	public CrudRepositoryImpl(MDocument documentMetadata, ConnectionPool connectionPoolReference) {
		super(documentMetadata, connectionPoolReference);
	}

	@Override
	public List<T> readAll() {
		try {
			List<T> results = new ArrayList<>();
			MongoCollection<Document> collectionCursor = connectionPoolReference.provideCollectionCursor(documentMetadata.getCollection());
			MongoCursor<Document> cursor = collectionCursor.find().iterator();
			while(cursor.hasNext()) {
				Document document = cursor.next();
				results.add(extractDocument(document, documentMetadata));
			}
			return results;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private T extractDocument(Document document, MDocument documentMedata) {
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
				}
				fieldMetadata.getRefField().set(result, fieldMetadata.getJavaType().cast(document.get(fieldMetadata.getNameInDatabase())));
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Optional<T> readById(Object id) {
		try {
			MongoCollection<Document> collectionCursor = connectionPoolReference.provideCollectionCursor(documentMetadata.getCollection());
			ObjectId objectId = new ObjectId(id.toString());
			Document document = collectionCursor.find(com.mongodb.client.model.Filters.eq(objectId)).first();
			T value = (!Util.isNull(document)) ? extractDocument(document, documentMetadata) : null;
			return Optional.ofNullable(value);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Document prepareDocument (T t, MDocument documentMetadata) {
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
					document.append(fieldMetadata.getNameInDatabase(), value);
				}
			}
			return document;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public T createOne(T t) {
		try {
			Document document = prepareDocument(t, documentMetadata);
			MongoCollection<Document> collectionCursor = connectionPoolReference.provideCollectionCursor(documentMetadata.getCollection());
			collectionCursor.insertOne(document);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Iterable<T> createMore(Iterable<T> objects) {
		try {
			Iterator<T> objectsIterator = objects.iterator();
			List<Document> documents = new ArrayList<>();
			while(objectsIterator.hasNext()) {
				documents.add(prepareDocument(objectsIterator.next(), documentMetadata));
			}
			MongoCollection<Document> collectionCursor = connectionPoolReference.provideCollectionCursor(documentMetadata.getCollection());
			collectionCursor.insertMany(documents);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public T updateOne(T t) {
		try {
			MongoCollection<Document> collectionCursor = connectionPoolReference.provideCollectionCursor(documentMetadata.getCollection());
			Object valueOfId = documentMetadata.getId().getRefField().get(t);
			if(Util.isNull(valueOfId)) {
				// TODO: Not value of ID present!
			}
			ObjectId objectId = new ObjectId(valueOfId.toString());
			collectionCursor.updateOne(com.mongodb.client.model.Filters.eq(objectId), prepareDocument(t, documentMetadata));
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Iterable<T> updateMore(Iterable<T> objects) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T deleteOne(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<T> deleteMore(Iterable<T> objects) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteAll() {
		// TODO Auto-generated method stub
		return false;
	}

}
