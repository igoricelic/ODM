package com.orm.v_1.SimpleDocumentMapper.repositories;

import java.util.List;

import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.Specification;
import com.orm.v_1.SimpleDocumentMapper.odm.structures.Page;
import com.orm.v_1.SimpleDocumentMapper.odm.structures.PageRequest;
import com.orm.v_1.SimpleDocumentMapper.odm.structures.SortRequest;

public interface PagingAndSortingRepository<T> extends SpecificationRepository<T> {
	
	public Page<T> readAll (PageRequest pageRequest);
	
	public List<T> readAll (SortRequest sortRequest);
	
	public Page<T> readAll (PageRequest pageRequest, SortRequest sortRequest);
	
	public Page<T> readBy (Specification<T> specification, PageRequest pageRequest);
	
	public List<T> readBy (Specification<T> specification, SortRequest sortRequest);
	
	public Page<T> readBy (Specification<T> specification, PageRequest pageRequest, SortRequest sortRequest);
	
}
