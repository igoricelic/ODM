package com.orm.v_1.SimpleDocumentMapper.interpreter.lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.orm.v_1.SimpleDocumentMapper.interpreter.lexer.model.Token;
import com.orm.v_1.SimpleDocumentMapper.interpreter.lexer.model.TokenProvider;
import com.orm.v_1.SimpleDocumentMapper.interpreter.lexer.model.TokenProviderImpl;
import com.orm.v_1.SimpleDocumentMapper.interpreter.lexer.model.TokenType;
import com.orm.v_1.SimpleDocumentMapper.model.MDocument;
import com.orm.v_1.SimpleDocumentMapper.model.exceptions.InvalidTokenException;

public class LexerProviderImpl implements LexerProvider {

	private TokenProvider tokenProvider;
	
	public LexerProviderImpl() {
		this.tokenProvider = new TokenProviderImpl();
	}
	
	@Override
	public List<Token> processing(String specificationText, MDocument documentMetadata) {
		List<Token> tokens = new ArrayList<>();
		Token currToken = null;
		Optional<Token> optionalPotentialToken = null;
		StringBuilder potentialTokenBuilder = new StringBuilder(100);
		MDocument currDocumentMetadata = documentMetadata;
		int index = 0;
		do {
			for (int i = index; i < specificationText.length(); i++) {
				potentialTokenBuilder.append(specificationText.charAt(i));
				optionalPotentialToken = tokenProvider.find(potentialTokenBuilder.toString(), currDocumentMetadata);
				if (optionalPotentialToken.isPresent()) {
					currToken = optionalPotentialToken.get();
					index = i + 1;
					if(index < specificationText.length() && specificationText.charAt(index) == '_') { 						
						index = index + 1;
						currToken.setJoin(true);
						currDocumentMetadata = currToken.getFieldMetadata().getEmbeddedDocumentMetadata();
						break;
					}
				}
			}
			if (currToken == null)
				throw new InvalidTokenException("Invalid token " + potentialTokenBuilder.substring(index));
			if(currToken.getTokenType() == TokenType.RESERVED_KEYWORD) currDocumentMetadata = documentMetadata;
			tokens.add(currToken);
			currToken = null;
			potentialTokenBuilder.setLength(0);
		} while (index < specificationText.length());
		
		return tokens;
	}

}
