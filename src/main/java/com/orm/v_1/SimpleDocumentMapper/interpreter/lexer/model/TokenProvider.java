package com.orm.v_1.SimpleDocumentMapper.interpreter.lexer.model;

import java.util.Optional;

import com.orm.v_1.SimpleDocumentMapper.model.MDocument;

public interface TokenProvider {
	
	public Optional<Token> find (String potentialToken, MDocument documentMetadata);

}
