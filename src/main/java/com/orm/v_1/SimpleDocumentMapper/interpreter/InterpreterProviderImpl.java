package com.orm.v_1.SimpleDocumentMapper.interpreter;

import java.lang.reflect.Method;
import java.util.List;

import com.orm.v_1.SimpleDocumentMapper.interpreter.lexer.LexerProvider;
import com.orm.v_1.SimpleDocumentMapper.interpreter.lexer.LexerProviderImpl;
import com.orm.v_1.SimpleDocumentMapper.interpreter.lexer.model.Token;
import com.orm.v_1.SimpleDocumentMapper.interpreter.model.MethodMetadata;
import com.orm.v_1.SimpleDocumentMapper.interpreter.semantic.SemanticProvider;
import com.orm.v_1.SimpleDocumentMapper.interpreter.semantic.SemanticProviderImpl;
import com.orm.v_1.SimpleDocumentMapper.model.MDocument;

public class InterpreterProviderImpl implements InterpreterProvider {
	
	private LexerProvider lexerProvider;
	
	private SemanticProvider semanticProvider;
	
	public InterpreterProviderImpl() {
		this.lexerProvider = new LexerProviderImpl();
		this.semanticProvider = new SemanticProviderImpl();
	}

	@Override
	public MethodMetadata interpretMethod(Method method, MDocument documentMetadata) {
		List<Token> tokens = lexerProvider.processing(method.getName(), documentMetadata);
		MethodMetadata methodMetadata = semanticProvider.processing(tokens);
//		method.getReturnType();
		return null;
	}

}
