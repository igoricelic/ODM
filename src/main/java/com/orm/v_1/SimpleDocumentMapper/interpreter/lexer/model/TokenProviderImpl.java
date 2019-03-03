package com.orm.v_1.SimpleDocumentMapper.interpreter.lexer.model;

import java.util.List;
import java.util.Optional;

import com.orm.v_1.SimpleDocumentMapper.model.MDocument;
import com.orm.v_1.SimpleDocumentMapper.model.MField;
import com.orm.v_1.SimpleDocumentMapper.model.util.Util;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.enums.Comparator;

public class TokenProviderImpl implements TokenProvider {
	
	private List<Token> reservedTokens = List.of(
			new Token("READ", TokenType.RESERVED_KEYWORD),
			new Token("COUNT", TokenType.RESERVED_KEYWORD),
			new Token("EXISTS", TokenType.RESERVED_KEYWORD),
			new Token("BY", TokenType.RESERVED_KEYWORD),
			new Token("OR", TokenType.RESERVED_KEYWORD),
			new Token("AND", TokenType.RESERVED_KEYWORD),
			new Token("EQ", TokenType.RESERVED_KEYWORD, Comparator.Equality),
			new Token("NEQ", TokenType.RESERVED_KEYWORD, Comparator.NotEquals),
			new Token("LT", TokenType.RESERVED_KEYWORD, Comparator.LessThan),
			new Token("LTE", TokenType.RESERVED_KEYWORD, Comparator.LessThanEquality),
			new Token("GT", TokenType.RESERVED_KEYWORD, Comparator.GreaterThan),
			new Token("GTE", TokenType.RESERVED_KEYWORD, Comparator.GreaterThanEquality),
			new Token("STARTSWITH", TokenType.RESERVED_KEYWORD, Comparator.StartsWith),
			new Token("ENDWITH", TokenType.RESERVED_KEYWORD, Comparator.EndsWith),
			new Token("CONTAINS", TokenType.RESERVED_KEYWORD, Comparator.Contains),
			new Token("BEFORE", TokenType.RESERVED_KEYWORD, Comparator.Before),
			new Token("AFTER", TokenType.RESERVED_KEYWORD, Comparator.After),
			new Token("IN", TokenType.RESERVED_KEYWORD, Comparator.In)
			);

	@Override
	public Optional<Token> find(String potentialToken, MDocument documentMetadata) {
		for(Token currToken: reservedTokens) {
			if(currToken.getValue().equalsIgnoreCase(potentialToken)) {
				return Optional.ofNullable(currToken);
			}
		}
		MField fieldMetadata = documentMetadata.getFieldMetadataByNameInModel(potentialToken);
		if(!Util.isNull(fieldMetadata)) return Optional.of(new Token(potentialToken, TokenType.VARIABLE, fieldMetadata));
		return Optional.empty();
	}
	

}
