package com.orm.v_1.SimpleDocumentMapper.odm.structures;

public class SortRequest {
	
	private String field;
	
	private Type type;
	
	public SortRequest(String field) {
		this(field, Type.ASC);
	}
	
	public SortRequest(String field, Type type) {
		this.field = field;
		this.type = type;
	}
	
	public enum Type {
		ASC, DESC;
	}
	
	public String getField() {
		return field;
	}
	
	public Type getType() {
		return type;
	}

}
