package com.orm.v_1.SimpleDocumentMapper.interpreter.model;

public class MethodMetadata {
	
	private MethodPrefixType methodPrefixType;
	
	private SpecificationProposal specificationProposal;
	
	private Class<?> returnType;
	
	private boolean isList, isOptional;
	
	public MethodPrefixType getMethodPrefixType() {
		return methodPrefixType;
	}
	
	public void setMethodPrefixType(MethodPrefixType methodPrefixType) {
		this.methodPrefixType = methodPrefixType;
	}
	
	public SpecificationProposal getSpecificationProposal() {
		return specificationProposal;
	}
	
	public void setSpecificationProposal(SpecificationProposal specificationProposal) {
		this.specificationProposal = specificationProposal;
	}
	
	public Class<?> getReturnType() {
		return returnType;
	}
	
	public void setReturnType(Class<?> returnType) {
		this.returnType = returnType;
	}
	
	public boolean isList() {
		return isList;
	}
	
	public void setList(boolean isList) {
		this.isList = isList;
	}
	
	public boolean isOptional() {
		return isOptional;
	}
	
	public void setOptional(boolean isOptional) {
		this.isOptional = isOptional;
	}

}
