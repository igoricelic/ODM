package com.orm.v_1.SimpleDocumentMapper.odm.specification.model.impl;

import java.util.List;

import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.Criterion;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.Specification;

public class SpecificationImpl<T> implements Specification<T> {
	
	private List<Criterion> criterions;

	@Override
	public List<Criterion> getCriterions() {
		return criterions;
	}

}
