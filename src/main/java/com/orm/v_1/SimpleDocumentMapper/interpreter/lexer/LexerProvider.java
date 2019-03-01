package com.orm.v_1.SimpleDocumentMapper.interpreter.lexer;

import java.util.List;

import com.orm.v_1.SimpleDocumentMapper.interpreter.lexer.model.Token;
import com.orm.v_1.SimpleDocumentMapper.model.MDocument;

public interface LexerProvider {
	
	public List<Token> processing(String specificationText, MDocument documentMetadata);

}
