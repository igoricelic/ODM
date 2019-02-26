package com.orm.v_1.SimpleDocumentMapper.odm.specification.model.impl;

import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.Criterion;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.enums.Comparator;

public class CriterionImpl implements Criterion {
	
	private String field;
	
	private Object value;
	
	private Comparator comparator;
	
	public CriterionImpl(String field, Object value, Comparator comparator) {
		this.field = field;
		this.value = value;
		this.comparator = comparator;
	}

	@Override
	public String getField() {
		return field;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public Comparator getComparator() {
		return comparator;
	}
	
}
