package com.orm.v_1.SimpleDocumentMapper.interpreter.semantic;

import java.util.List;

import com.orm.v_1.SimpleDocumentMapper.interpreter.lexer.model.Token;
import com.orm.v_1.SimpleDocumentMapper.interpreter.lexer.model.TokenType;
import com.orm.v_1.SimpleDocumentMapper.interpreter.model.CriterionProposal;
import com.orm.v_1.SimpleDocumentMapper.interpreter.model.MethodMetadata;
import com.orm.v_1.SimpleDocumentMapper.interpreter.model.MethodPrefixType;
import com.orm.v_1.SimpleDocumentMapper.interpreter.model.SpecificationProposal;
import com.orm.v_1.SimpleDocumentMapper.model.util.Util;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.enums.Operator;

public class SemanticProviderImpl implements SemanticProvider {

	@Override
	public MethodMetadata processing(List<Token> tokens) {
		MethodMetadata methodMetadata = new MethodMetadata();
		Token currToken = null, tempToken = null;
		if(tokens.size() < 3) {
			// TODO: Exception
		}
		currToken = tokens.get(0);
		if(!currToken.getValue().equalsIgnoreCase("READ") && !currToken.getValue().equalsIgnoreCase("COUNT") && !currToken.getValue().equalsIgnoreCase("EXISTS")) {
			// TODO: Exception
		}
		methodMetadata.setMethodPrefixType(MethodPrefixType.valueOf(currToken.getValue()));
		currToken = tokens.get(1);
		if(!currToken.getValue().equalsIgnoreCase("BY")) {
			// TODO: Exception
		}
		SpecificationProposal specificationProposal = new SpecificationProposal(), tempSpecificationProposal = null;
		CriterionProposal tempCriterion = null;
		for(int i=2; i<tokens.size()-1; i++) {
			currToken = tokens.get(i);
			if(currToken.getValue().equals("AND") || currToken.getValue().equals("OR")) {
				if(Util.isNull(tempCriterion)) {
					// TODO: Exception
				}
				Operator operator = currToken.getValue().equals("AND") ? Operator.And : Operator.Or;
				if(Util.isNull(specificationProposal.getOperator()))
					specificationProposal.setOperator(operator);
				else {
					// proveriti
					tempSpecificationProposal = specificationProposal;
					specificationProposal = new SpecificationProposal();
					specificationProposal.setOperator(operator);
					specificationProposal.getSpecificationProposals().add(tempSpecificationProposal);
				}
			}
			if(currToken.getTokenType() == TokenType.VARIABLE) {
				tempCriterion = new CriterionProposal(currToken.getValue(), currToken.getFieldMetadata());
				tempToken = tokens.get(i+1);
				if(Util.isNull(tempToken.getComparator())) {
					// TODO: Exception
					// nakon varijable mora stajati comparator
				}
				i++;
				tempCriterion.setComparator(tempToken.getComparator());
				specificationProposal.getCriterionProposals().add(tempCriterion);
			}
		}
		methodMetadata.setSpecificationProposal(specificationProposal);
		return methodMetadata;
	}

}
