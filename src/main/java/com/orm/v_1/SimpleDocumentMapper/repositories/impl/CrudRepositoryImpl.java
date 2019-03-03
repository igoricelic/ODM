package com.orm.v_1.SimpleDocumentMapper.repositories.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.orm.v_1.SimpleDocumentMapper.model.MDocument;
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
			return objects;
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
			Document document = new Document();
			document.append("$set", prepareDocument(t, documentMetadata));
			collectionCursor.updateOne(com.mongodb.client.model.Filters.eq(objectId), document);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Iterable<T> updateMore(Iterable<T> objects) {
		try {
			Iterator<T> objectsIterator = objects.iterator();
			while(objectsIterator.hasNext()) {
				updateOne(objectsIterator.next());
			}
			return objects;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public T deleteOne(T t) {
		try {
			MongoCollection<Document> collectionCursor = connectionPoolReference.provideCollectionCursor(documentMetadata.getCollection());
			Object valueOfId = documentMetadata.getId().getRefField().get(t);
			if(Util.isNull(valueOfId)) {
				// TODO: Not value of ID present!
			}
			ObjectId objectId = new ObjectId(valueOfId.toString());
			collectionCursor.deleteOne(com.mongodb.client.model.Filters.eq(objectId));
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Iterable<T> deleteMore(Iterable<T> objects) {
		try {
			Iterator<T> objectsIterator = objects.iterator();
			while(objectsIterator.hasNext()) {
				deleteOne(objectsIterator.next());
			}
			return objects;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteAll() {
		// TODO Auto-generated method stub
		return false;
	}

}
