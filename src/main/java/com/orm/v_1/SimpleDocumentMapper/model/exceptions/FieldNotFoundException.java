package com.orm.v_1.SimpleDocumentMapper.model.exceptions;

public class FieldNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public FieldNotFoundException (String mesage) {
		this.message = mesage;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}

}
