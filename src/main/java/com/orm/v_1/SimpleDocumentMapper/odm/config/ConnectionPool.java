package com.orm.v_1.SimpleDocumentMapper.odm.config;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public interface ConnectionPool {
	
	public MongoDatabase provideConnection();
	
	public MongoCollection<Document> provideCollectionCursor(String collection);

}
