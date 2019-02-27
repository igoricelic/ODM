package com.orm.v_1.SimpleDocumentMapper.odm.structures;

import java.util.List;

public class PageImpl<T> implements Page<T> {
	
	private List<T> content;
	
	private int page, size, numberOfElements, totalPages, totalElements;
	
	private boolean firstPage, lastPage;
	
	public PageImpl(List<T> content, PageRequest pageRequest, int totalElements) {
		this.content = content;
		this.page = pageRequest.getPage();
		this.size = pageRequest.getSize();
		this.numberOfElements = content.size();
		this.totalPages = (int) Math.ceil(totalElements / pageRequest.getSize());
		this.totalElements = totalElements;
		this.firstPage = (pageRequest.getPage() == 0) ? true : false;
		this.lastPage = (pageRequest.getPage() == totalPages) ? true : false;
	}

	@Override
	public List<T> getContent() {
		return content;
	}

	@Override
	public int getPage() {
		return page;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public int numberOfElements() {
		return numberOfElements;
	}

	@Override
	public int totalPages() {
		return totalPages;
	}

	@Override
	public int totalElements() {
		return totalElements;
	}

	@Override
	public boolean getFirstPage() {
		return firstPage;
	}

	@Override
	public boolean getLastPage() {
		return lastPage;
	}

}
