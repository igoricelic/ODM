package com.orm.v_1.SimpleDocumentMapper.odm.specification.model;

import java.util.List;

public interface Specification<T> {
	
	public List<Criterion> getCriterions();

}
