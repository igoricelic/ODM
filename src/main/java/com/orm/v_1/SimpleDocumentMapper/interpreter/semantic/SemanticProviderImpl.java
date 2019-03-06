package com.orm.v_1.SimpleDocumentMapper.interpreter.semantic;

import java.util.List;

import com.orm.v_1.SimpleDocumentMapper.interpreter.lexer.model.Token;
import com.orm.v_1.SimpleDocumentMapper.interpreter.lexer.model.TokenType;
import com.orm.v_1.SimpleDocumentMapper.interpreter.model.CriterionProposal;
import com.orm.v_1.SimpleDocumentMapper.interpreter.model.MethodMetadata;
import com.orm.v_1.SimpleDocumentMapper.interpreter.model.MethodPrefixType;
import com.orm.v_1.SimpleDocumentMapper.interpreter.model.SpecificationProposal;
import com.orm.v_1.SimpleDocumentMapper.model.exceptions.InvalidMethodSpecificationException;
import com.orm.v_1.SimpleDocumentMapper.model.exceptions.InvalidTokenException;
import com.orm.v_1.SimpleDocumentMapper.model.util.Util;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.enums.Operator;

public class SemanticProviderImpl implements SemanticProvider {

	@Override
	public MethodMetadata processing(List<Token> tokens) {
		MethodMetadata methodMetadata = new MethodMetadata();
		Token currToken = null, tempToken = null;
		int argumentPosition = 0;
		if(tokens.size() < 3) {
			throw new InvalidMethodSpecificationException("Invalid method specification!");
		}
		currToken = tokens.get(0);
		if(!currToken.getValue().equalsIgnoreCase("READ") && !currToken.getValue().equalsIgnoreCase("COUNT") && !currToken.getValue().equalsIgnoreCase("EXISTS")) {
			throw new InvalidTokenException("The first token in method specification must be READ, COUNT or EXISTS!");
		}
		methodMetadata.setMethodPrefixType(MethodPrefixType.valueOf(currToken.getValue()));
		currToken = tokens.get(1);
		if(!currToken.getValue().equalsIgnoreCase("BY")) {
			throw new InvalidTokenException("On this position, token must be BY!");
		}
		SpecificationProposal specificationProposal = new SpecificationProposal(), tempSpecificationProposal = null;
		CriterionProposal tempCriterion = null;
		for(int i=2; i<tokens.size()-1; i++) {
			currToken = tokens.get(i);
			if(currToken.getValue().equals("AND") || currToken.getValue().equals("OR")) {
				if(Util.isNull(tempCriterion)) {
					throw new InvalidTokenException("Invalid token position!");
				}
				Operator operator = currToken.getValue().equals("AND") ? Operator.And : Operator.Or;
				if(Util.isNull(specificationProposal.getOperator()))
					specificationProposal.setOperator(operator);
				else {
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
					throw new InvalidTokenException("After variable must be comparator!");
				}
				i++;
				tempCriterion.setArgumentPosition(argumentPosition++);
				tempCriterion.setComparator(tempToken.getComparator());
				specificationProposal.getCriterionProposals().add(tempCriterion);
			}
		}
		methodMetadata.setTotalArguments(argumentPosition);
		methodMetadata.setSpecificationProposal(specificationProposal);
		return methodMetadata;
	}

}
