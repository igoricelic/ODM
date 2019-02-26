package com.orm.v_1.SimpleDocumentMapper.odm.specification.model.impl;

import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.Criterion;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.enums.Comparator;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.enums.Operator;

public class CriterionImpl implements Criterion {
	
	private String field;
	
	private Object value;
	
	private Comparator comparator;
	
	private Operator operator;

	@Override
	public String getField() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator getComparator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Operator getOperator() {
		// TODO Auto-generated method stub
		return null;
	}

}
