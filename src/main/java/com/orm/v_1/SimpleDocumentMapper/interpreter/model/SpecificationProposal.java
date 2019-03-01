package com.orm.v_1.SimpleDocumentMapper.interpreter.model;

import java.util.ArrayList;
import java.util.List;

import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.enums.Operator;

public class SpecificationProposal {
	
	private Operator operator;
	
	private List<CriterionProposal> criterionProposals;
	
	private List<SpecificationProposal> specificationProposals;
	
	public SpecificationProposal() {
		this.criterionProposals = new ArrayList<>();
		this.specificationProposals = new ArrayList<>();
	}
	
	public Operator getOperator() {
		return operator;
	}
	
	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	
	public List<CriterionProposal> getCriterionProposals() {
		return criterionProposals;
	}
	
	public List<SpecificationProposal> getSpecificationProposals() {
		return specificationProposals;
	}

}
