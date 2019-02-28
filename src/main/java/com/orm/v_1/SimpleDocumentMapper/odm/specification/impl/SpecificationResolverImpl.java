package com.orm.v_1.SimpleDocumentMapper.odm.specification.impl;

import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
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

	private static final String DOT = ".";
	
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
				return com.mongodb.client.model.Filters.regex(nameInDatabase, Pattern.compile(criterion.getValue() + "([\\w]+)?"));
			case EndsWith:
				return com.mongodb.client.model.Filters.regex(nameInDatabase, Pattern.compile("([\\w]+)?" + criterion.getValue()));
			case Contains:
				return com.mongodb.client.model.Filters.regex(nameInDatabase, Pattern.compile("([\\w]+)?" + criterion.getValue()) + "([\\w]+)?");
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
		StringTokenizer tokens = new StringTokenizer(nameInModel, DOT);
		StringBuilder stringBuilder = new StringBuilder();
		MField fieldMetadata = null;
		MDocument currDocumentMetadata = documentMetadata;
		while(tokens.hasMoreElements()) {
			fieldMetadata = currDocumentMetadata.getFieldMetadataByNameInModel(tokens.nextElement().toString());
			if(Util.isNull(fieldMetadata)) {
				// TODO: Exception
			}
			stringBuilder.append(fieldMetadata.getNameInDatabase());
			if(tokens.hasMoreElements()) {
				currDocumentMetadata = fieldMetadata.getEmbeddedDocumentMetadata();
				stringBuilder.append(DOT);
			}
		}
		return stringBuilder.toString();
	}


}
