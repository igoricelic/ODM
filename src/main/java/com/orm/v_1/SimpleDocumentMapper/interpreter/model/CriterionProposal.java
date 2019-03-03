package com.orm.v_1.SimpleDocumentMapper.interpreter.model;

import com.orm.v_1.SimpleDocumentMapper.model.MField;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.enums.Comparator;

public class CriterionProposal {
	
	private String field;
	
	private Comparator comparator;
	
	private int argumentPosition;
	
	private MField fieldMetadata;
	
	public CriterionProposal(String field, MField fieldMetadata) {
		this.field = field;
		this.fieldMetadata = fieldMetadata;
	}
	
	public String getField() {
		return field;
	}
	
	public Comparator getComparator() {
		return comparator;
	}
	
	public void setComparator(Comparator comparator) {
		this.comparator = comparator;
	}
	
	public MField getFieldMetadata() {
		return fieldMetadata;
	}
	
	public int getArgumentPosition() {
		return argumentPosition;
	}
	
	public void setArgumentPosition(int argumentPosition) {
		this.argumentPosition = argumentPosition;
	}

}
