package com.orm.v_1.SimpleDocumentMapper.odm.specification.model;

import java.util.List;

import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.enums.Operator;

public interface Specification<T> {
	
	public List<Criterion> getCriterions();
	
	public List<Specification<T>> getSpecifications();
	
	public Operator getOperator();

}
