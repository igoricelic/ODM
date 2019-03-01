package com.orm.v_1.SimpleDocumentMapper.interpreter.lexer.model;

import com.orm.v_1.SimpleDocumentMapper.model.MField;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.enums.Comparator;

public class Token {
	
	private String value;
	
	private TokenType tokenType;
	
	private Comparator comparator;
	
	private MField fieldMetadata;
	
	private boolean join;
	
	public Token(String value, TokenType tokenType) {
		this.value = value;
		this.tokenType = tokenType;
	}
	
	public Token(String value, TokenType tokenType, Comparator comparator) {
		this(value, tokenType);
		this.comparator = comparator;
	}
	
	public Token(String value, TokenType tokenType, MField fieldMetadata) {
		this(value, tokenType);
		this.fieldMetadata = fieldMetadata;
	}
	
	public String getValue() {
		return value;
	}
	
	public TokenType getTokenType() {
		return tokenType;
	}
	
	public Comparator getComparator() {
		return comparator;
	}
	
	public MField getFieldMetadata() {
		return fieldMetadata;
	}
	
	public boolean isJoin() {
		return join;
	}
	
	public void setJoin(boolean join) {
		this.join = join;
	}

}
