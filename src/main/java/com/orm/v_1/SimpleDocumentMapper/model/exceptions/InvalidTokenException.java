package com.orm.v_1.SimpleDocumentMapper.model.exceptions;

public class InvalidTokenException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private String msg;
	
	public InvalidTokenException(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String getMessage() {
		return this.msg;
	}

}
