package com.orm.v_1.SimpleDocumentMapper.odm.specification.model.builders;

import java.util.ArrayList;
import java.util.List;

import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.Criterion;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.Specification;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.enums.Comparator;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.enums.Operator;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.impl.CriterionImpl;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.impl.SpecificationImpl;

public class SpecificationBuilder<T> {
	
	private List<Criterion> criterions;
	
	private List<Specification<?>> specifications;
	
	private Operator operator;
	
	public SpecificationBuilder() {
		this.criterions = new ArrayList<>();
		this.specifications = new ArrayList<>();
		this.operator = Operator.And;
	}
	
	public SpecificationBuilder addCriterion (String field, Object value, Comparator comparator) {
		this.criterions.add(new CriterionImpl(field, value, comparator));
		return this;
	}
	
	public <T> SpecificationBuilder where (Specification<T> specification) {
		this.specifications.add(specification);
		return this;
	}
	
	public SpecificationBuilder operator (Operator operator) {
		this.operator = operator;
		return this;
	}
	
	public Specification<T> build() {
		return new SpecificationImpl(operator, criterions, specifications);
	}
	

}
