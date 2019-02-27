package com.orm.v_1.SimpleDocumentMapper.odm.specification.impl;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.conversions.Bson;

import com.orm.v_1.SimpleDocumentMapper.model.MDocument;
import com.orm.v_1.SimpleDocumentMapper.model.MField;
import com.orm.v_1.SimpleDocumentMapper.model.util.Util;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.SpecificationResolver;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.Criterion;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.Specification;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.enums.Operator;

public class SpecificationResolverImpl implements SpecificationResolver {

	private MDocument documentMetadata;

	public SpecificationResolverImpl(MDocument documentMetadata) {
		this.documentMetadata = documentMetadata;
	}

	@Override
	public <T> Bson processing(Specification<T> specification) {
		Bson result = null;
		List<Bson> conditions = null;
		if (specification.getSpecifications().size() > 0) {
			conditions = specification.getSpecifications().stream().map(spec -> processing(spec)).collect(Collectors.toList());
		}
		if (specification.getCriterions().size() > 0) {
			conditions = specification.getCriterions().stream().map(criterion -> processingSingleCriterion(criterion)).collect(Collectors.toList());
		}
		
		if(Util.isNull(conditions)) {
			// TODO: exception
		}
		
		if(conditions.size() > 1) {
			if(specification.getOperator() == Operator.And)
				result = com.mongodb.client.model.Filters.and(conditions);
			else
				result = com.mongodb.client.model.Filters.or(conditions);
		} else {
			result = conditions.get(0);
		}

		return result;
	}
	
	private Bson processingSingleCriterion (Criterion criterion) {
		String nameInDatabase = prepareFieldName(criterion.getField());
		System.out.println(">>> "+nameInDatabase);
		/**
		 * Ostalo je dopuniti logikom za ugnjezdene objekte npr u odjektu user imamo ugnjezdeni objekat
		 * City -> city.address ili visestruki nivo city.address.street
		 */
		if(nameInDatabase == null) {
			// TODO: Exception
		}
		switch (criterion.getComparator()) {
			case Equality:
				return com.mongodb.client.model.Filters.eq(nameInDatabase, criterion.getValue());
			case LessThan:
				return com.mongodb.client.model.Filters.lt(nameInDatabase, criterion.getValue());
			case LessThanEquality:
				return com.mongodb.client.model.Filters.lte(nameInDatabase, criterion.getValue());
			case GreaterThan:
				return com.mongodb.client.model.Filters.gt(nameInDatabase, criterion.getValue());
			case GreaterThanEquality:
				return com.mongodb.client.model.Filters.gte(nameInDatabase, criterion.getValue());
			case NotEquals:
				return com.mongodb.client.model.Filters.ne(nameInDatabase, criterion.getValue());
			case StartsWith:
				break;
			case EndsWith:
				break;
			case Contains:
				break;
			case Before:
				break;
			case After:
				break;
			default:
				break;
		}
		return null;
	}
	
	private String prepareFieldName (String nameInModel) {
		String[] tokens = nameInModel.split(".");
		Iterator<String> tokenIterator = List.of(tokens).iterator();
		StringBuilder stringBuilder = new StringBuilder();
		MField fieldMetadata = null;
		
		while(tokenIterator.hasNext()) {
			fieldMetadata = documentMetadata.getFieldMetadataByNameInModel(tokenIterator.next());
			if(Util.isNull(fieldMetadata)) {
				// TODO: Exception
			}
			stringBuilder.append(fieldMetadata.getNameInDatabase());
			if(tokenIterator.hasNext()) stringBuilder.append(".");
		}
		
		return stringBuilder.toString();
	}


}
