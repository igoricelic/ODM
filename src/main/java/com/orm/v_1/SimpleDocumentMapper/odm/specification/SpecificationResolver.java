package com.orm.v_1.SimpleDocumentMapper.odm.specification;

import org.bson.conversions.Bson;

import com.orm.v_1.SimpleDocumentMapper.interpreter.model.SpecificationProposal;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.Specification;

public interface SpecificationResolver {
	
	public <T> Bson processing (Specification<T> specification);
	
	public <T> Specification<T> prepareSpecificaiton (SpecificationProposal specificationProposal, Object[] args);

}
