package com.orm.v_1.SimpleDocumentMapper.interpreter.model;

import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.enums.Comparator;

public class CriterionProposal {
	
	private String field;
	
	private Comparator comparator;
	
	private Class<?> javaType;
	
	public CriterionProposal(String field, Comparator comparator, Class<?> javaType) {
		this.field = field;
		this.comparator = comparator;
		this.javaType = javaType;
	}
	
	public String getField() {
		return field;
	}
	
	public Comparator getComparator() {
		return comparator;
	}
	
	public Class<?> getJavaType() {
		return javaType;
	}

}
