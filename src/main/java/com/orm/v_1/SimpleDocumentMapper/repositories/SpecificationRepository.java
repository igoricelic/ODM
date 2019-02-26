package com.orm.v_1.SimpleDocumentMapper.repositories;

import java.util.List;

import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.Specification;

public interface SpecificationRepository<T> extends CrudRepository<T> {
	
	public List<T> readBy (Specification specification);
	
	public int countBy (Specification specification);
	
	public boolean existBy (Specification specification);

}
