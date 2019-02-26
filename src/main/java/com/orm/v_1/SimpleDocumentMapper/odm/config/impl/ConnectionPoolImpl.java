package com.orm.v_1.SimpleDocumentMapper.odm.config.impl;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.orm.v_1.SimpleDocumentMapper.odm.config.ConnectionPool;

public class ConnectionPoolImpl implements ConnectionPool {
	
	private String database;
	
	private MongoClient mongoClient;
	
	public ConnectionPoolImpl(String database, String host, int port) {
		this.database = database;
		this.mongoClient = new MongoClient(host, port);
	}

	@Override
	public MongoDatabase provideConnection() {
		return this.mongoClient.getDatabase(database);
	}

	@Override
	public MongoCollection<Document> provideCollectionCursor(String collection) {
		return provideConnection().getCollection(collection);
	}

}
