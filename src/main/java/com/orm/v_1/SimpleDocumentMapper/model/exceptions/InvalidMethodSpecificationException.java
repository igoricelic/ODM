package com.orm.v_1.SimpleDocumentMapper.model.exceptions;

public class InvalidMethodSpecificationException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public InvalidMethodSpecificationException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}

}
