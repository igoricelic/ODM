package com.orm.v_1.SimpleDocumentMapper.interpreter.lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.orm.v_1.SimpleDocumentMapper.interpreter.lexer.model.Token;
import com.orm.v_1.SimpleDocumentMapper.interpreter.lexer.model.TokenProvider;
import com.orm.v_1.SimpleDocumentMapper.interpreter.lexer.model.TokenProviderImpl;
import com.orm.v_1.SimpleDocumentMapper.model.MDocument;
import com.orm.v_1.SimpleDocumentMapper.model.exceptions.InvalidTokenException;
import com.orm.v_1.SimpleDocumentMapper.model.util.Util;

public class LexerProviderImpl implements LexerProvider {
	
	private static final String DOT = ".";

	private TokenProvider tokenProvider;
	
	public LexerProviderImpl() {
		this.tokenProvider = new TokenProviderImpl();
	}
	
	@Override
	public List<Token> processing(String specificationText, MDocument documentMetadata) {
		List<Token> tokens = new ArrayList<>();
		Token currToken = null, prevToken = null;
		Optional<Token> optionalPotentialToken = null;
		StringBuilder potentialTokenBuilder = new StringBuilder(100);
		StringBuilder compositeTokenValue = new StringBuilder(100);
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
						if(compositeTokenValue.toString().length() > 0) compositeTokenValue.append(DOT);
						compositeTokenValue.append(currToken.getFieldMetadata().getNameInDatabase());
						break;
					}
					if(currToken.isJoin()|| (!Util.isNull(prevToken) && prevToken.isJoin())) {
						if(compositeTokenValue.toString().length() > 0) compositeTokenValue.append(DOT);
						compositeTokenValue.append(currToken.getFieldMetadata().getNameInDatabase());
					}
				}
			}
			if (currToken == null)
				throw new InvalidTokenException("Invalid token " + potentialTokenBuilder.substring(index));
			if(!currToken.isJoin()) {
				currDocumentMetadata = documentMetadata;
				if(compositeTokenValue.toString().length() > 0) {
					currToken.setValue(compositeTokenValue.toString());
					compositeTokenValue.setLength(0);
				}
				tokens.add(currToken);
			}
			prevToken = currToken;
			currToken = null;
			potentialTokenBuilder.setLength(0);
		} while (index < specificationText.length());
		
		return tokens;
	}

}
