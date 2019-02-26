package com.orm.v_1.SimpleDocumentMapper.odm.structures;

import java.util.List;

public interface Page<T> {
	
	public List<T> getContent();
	
	public int getPage();
	
	public int getSize();
	
	public int numberOfElements();
	
	public int totalPages();
	
	public int totalElements();
	
	public boolean getFirstPage();
	
	public boolean getLastPage();

}
