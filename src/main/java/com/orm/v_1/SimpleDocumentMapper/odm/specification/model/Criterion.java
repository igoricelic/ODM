package com.orm.v_1.SimpleDocumentMapper.odm.specification.model;

import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.enums.Comparator;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.enums.Operator;

public interface Criterion {
	
	public String getField();
	
	public Object getValue();
	
	public Comparator getComparator();
	
	public Operator getOperator();

}
