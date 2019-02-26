package com.orm.v_1.SimpleDocumentMapper.repositories.impl;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.orm.v_1.SimpleDocumentMapper.model.MDocument;
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

}
