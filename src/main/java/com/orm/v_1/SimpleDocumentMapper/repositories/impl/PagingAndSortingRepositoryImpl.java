package com.orm.v_1.SimpleDocumentMapper.repositories.impl;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.orm.v_1.SimpleDocumentMapper.model.MDocument;
import com.orm.v_1.SimpleDocumentMapper.odm.config.ConnectionPool;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.Specification;
import com.orm.v_1.SimpleDocumentMapper.odm.structures.Page;
import com.orm.v_1.SimpleDocumentMapper.odm.structures.PageImpl;
import com.orm.v_1.SimpleDocumentMapper.odm.structures.PageRequest;
import com.orm.v_1.SimpleDocumentMapper.odm.structures.SortRequest;
import com.orm.v_1.SimpleDocumentMapper.repositories.PagingAndSortingRepository;

public class PagingAndSortingRepositoryImpl<T> extends SpecificationRepositoryImpl<T> implements PagingAndSortingRepository<T> {

	public PagingAndSortingRepositoryImpl(MDocument documentMetadata, ConnectionPool connectionPoolReference) {
		super(documentMetadata, connectionPoolReference);
	}

	@Override
	public Page<T> readAll(PageRequest pageRequest) {
		try {
			int page = pageRequest.getPage();
			int size = pageRequest.getSize();
			List<T> content = new ArrayList<>();
			MongoCollection<Document> collectionCursor = connectionPoolReference.provideCollectionCursor(documentMetadata.getCollection());
			MongoCursor<Document> cursor = collectionCursor.find().skip(size * (page - 1)).limit(size).iterator();
			while(cursor.hasNext()) {
				Document document = cursor.next();
				content.add(extractDocument(document, documentMetadata));
			}
			return new PageImpl<>(content, pageRequest, (int) collectionCursor.countDocuments());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<T> readAll(SortRequest sortRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<T> readAll(PageRequest pageRequest, SortRequest sortRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<T> readBy(Specification<T> specification, PageRequest pageRequest) {
		try {
			int page = pageRequest.getPage();
			int size = pageRequest.getSize();
			List<T> content = new ArrayList<>();
			MongoCollection<Document> collectionCursor = connectionPoolReference.provideCollectionCursor(documentMetadata.getCollection());
			Bson searchFilter = specificationResolver.processing(specification);
			MongoCursor<Document> cursor = collectionCursor.find(searchFilter).skip(size * (page - 1)).limit(size).iterator();
			while(cursor.hasNext()) {
				Document document = cursor.next();
				content.add(extractDocument(document, documentMetadata));
			}
			return new PageImpl<>(content, pageRequest, (int) collectionCursor.countDocuments());
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<T> readBy(Specification<T> specification, SortRequest sortRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<T> readBy(Specification<T> specification, PageRequest pageRequest, SortRequest sortRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
