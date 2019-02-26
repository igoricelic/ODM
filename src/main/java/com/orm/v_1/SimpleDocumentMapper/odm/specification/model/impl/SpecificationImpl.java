package com.orm.v_1.SimpleDocumentMapper.odm.specification.model.impl;

import java.util.List;

import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.Criterion;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.Specification;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.enums.Operator;

public class SpecificationImpl<T> implements Specification<T> {
	
	private List<Criterion> criterions;
	
	private List<Specification<T>> specifications;
	
	private Operator operator;
	
	public SpecificationImpl(Operator operator, List<Criterion> criterions, List<Specification<T>> specifications) {
		this.operator = operator;
		this.criterions = criterions;
		this.specifications = specifications;
	}

	@Override
	public List<Criterion> getCriterions() {
		return criterions;
	}

	@Override
	public List<Specification<T>> getSpecifications() {
		return specifications;
	}

	@Override
	public Operator getOperator() {
		return operator;
	}

}
