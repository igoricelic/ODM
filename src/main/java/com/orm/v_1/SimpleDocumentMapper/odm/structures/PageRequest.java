package com.orm.v_1.SimpleDocumentMapper.odm.structures;

public class PageRequest {
	
	private int page;
	
	private int size;
	
	public PageRequest(int page, int size) {
		this.page = page;
		this.size = size;
	}
	
	public int getPage() {
		return page;
	}
	
	public int getSize() {
		return size;
	}

}
