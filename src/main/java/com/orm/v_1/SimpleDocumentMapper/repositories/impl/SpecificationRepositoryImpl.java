package com.orm.v_1.SimpleDocumentMapper.repositories.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.orm.v_1.SimpleDocumentMapper.model.MDocument;
import com.orm.v_1.SimpleDocumentMapper.odm.config.ConnectionPool;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.SpecificationResolver;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.impl.SpecificationResolverImpl;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.Specification;
import com.orm.v_1.SimpleDocumentMapper.repositories.SpecificationRepository;

public class SpecificationRepositoryImpl<T> extends CrudRepositoryImpl<T> implements SpecificationRepository<T> {

	protected SpecificationResolver specificationResolver;
	
	public SpecificationRepositoryImpl(MDocument documentMetadata, ConnectionPool connectionPoolReference) {
		super(documentMetadata, connectionPoolReference);
		this.specificationResolver = new SpecificationResolverImpl(documentMetadata);
	}

	@Override
	public List<T> readBy(Specification<T> specification) {
		try {
			List<T> results = new ArrayList<>();
			MongoCollection<Document> collectionCursor = connectionPoolReference.provideCollectionCursor(documentMetadata.getCollection());
			Bson searchFilter = specificationResolver.processing(specification);
			Iterator<Document> documentIterator = collectionCursor.find(searchFilter).iterator();
			while(documentIterator.hasNext()) results.add(extractDocument(documentIterator.next(), documentMetadata));
			return results;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public long countBy(Specification<T> specification) {
		try {
			MongoCollection<Document> collectionCursor = connectionPoolReference.provideCollectionCursor(documentMetadata.getCollection());
			Bson searchFilter = specificationResolver.processing(specification);
			return collectionCursor.countDocuments(searchFilter);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public boolean existBy(Specification<T> specification) {
		return (countBy(specification) > 0) ? true : false;
	}

	@Override
	public List<T> deleteBy(Specification<T> specification) {
		// TODO Auto-generated method stub
		return null;
	}

}
