package com.orm.v_1.SimpleDocumentMapper.interpreter.semantic;

import java.util.List;

import com.orm.v_1.SimpleDocumentMapper.interpreter.lexer.model.Token;
import com.orm.v_1.SimpleDocumentMapper.interpreter.model.SpecificationProposal;

public interface SemanticProvider {
	
	public SpecificationProposal processing (List<Token> tokens);

}
