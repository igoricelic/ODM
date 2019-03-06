package com.orm.v_1.SimpleDocumentMapper.model.exceptions;

public class NotCompatibleTypesException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public NotCompatibleTypesException (String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}

}
