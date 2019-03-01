package com.orm.v_1.SimpleDocumentMapper.interpreter.semantic;

import java.util.List;

import com.orm.v_1.SimpleDocumentMapper.interpreter.lexer.model.Token;
import com.orm.v_1.SimpleDocumentMapper.interpreter.model.SpecificationProposal;

public class SemanticProviderImpl implements SemanticProvider {

	@Override
	public SpecificationProposal processing(List<Token> tokens) {
		SpecificationProposal result = new SpecificationProposal();
		Token currToken = null;
		if(tokens.size() < 3) {
			// TODO: Exception
		}
		currToken = tokens.get(0);
		if(!currToken.getValue().equalsIgnoreCase("READ") && !currToken.getValue().equalsIgnoreCase("COUNT") && !currToken.getValue().equalsIgnoreCase("EXISTS")) {
			// TODO: Exception
		}
		currToken = tokens.get(1);
		if(!currToken.getValue().equalsIgnoreCase("BY")) {
			// TODO: Exception
		}
		for(int i=2; i<tokens.size(); i++) {
			currToken = tokens.get(i);
			if(currToken.getValue() == "AND") {
				
			}
			
		}
		
		return null;
	}

}
