package com.orm.v_1.SimpleDocumentMapper.interpreter;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import com.orm.v_1.SimpleDocumentMapper.interpreter.lexer.LexerProvider;
import com.orm.v_1.SimpleDocumentMapper.interpreter.lexer.LexerProviderImpl;
import com.orm.v_1.SimpleDocumentMapper.interpreter.lexer.model.Token;
import com.orm.v_1.SimpleDocumentMapper.interpreter.model.MethodMetadata;
import com.orm.v_1.SimpleDocumentMapper.interpreter.model.MethodPrefixType;
import com.orm.v_1.SimpleDocumentMapper.interpreter.semantic.SemanticProvider;
import com.orm.v_1.SimpleDocumentMapper.interpreter.semantic.SemanticProviderImpl;
import com.orm.v_1.SimpleDocumentMapper.model.MDocument;
import com.orm.v_1.SimpleDocumentMapper.odm.structures.Page;

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
		return resolveReturnType(methodMetadata, method, documentMetadata);
	}
	
	private MethodMetadata resolveReturnType (MethodMetadata methodMetadata, Method method, MDocument documentMetadata) {		
		if(!(methodMetadata.getMethodPrefixType() == MethodPrefixType.READ)) return methodMetadata;
		Type genericReturnType = method.getGenericReturnType();
		Class<?> returnType = method.getReturnType();
		boolean isResolvedBaseType = false;
		while(!isResolvedBaseType) {
			if(genericReturnType instanceof ParameterizedType) {
				ParameterizedType paramType = (ParameterizedType) genericReturnType;
	            Type[] argTypes = paramType.getActualTypeArguments();
	            genericReturnType = paramType.getRawType();
	            if(Optional.class.equals(genericReturnType)) {
	            	methodMetadata.setOptional(true);
	            } else if(List.class.equals(genericReturnType)) {
	            	methodMetadata.setList(true);
	            } else if(Page.class.equals(genericReturnType)) {
	            	methodMetadata.setPage(true);
	            }
	            if(argTypes[0] instanceof ParameterizedType) {
	            	genericReturnType = argTypes[0];
	            } else {
	            	returnType = (Class<?>) argTypes[0];
	            }
			} else {
				if(!returnType.equals(documentMetadata.getEntityClass())) {
					// TODO: Exception
				}
				methodMetadata.setReturnType(returnType);
				isResolvedBaseType = true;
			}
		}
		
		return methodMetadata;
	}

}
