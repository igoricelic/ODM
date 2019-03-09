package com.orm.v_1.SimpleDocumentMapper.interpreter.semantic;

import java.lang.reflect.Parameter;
import java.util.List;

import com.orm.v_1.SimpleDocumentMapper.interpreter.lexer.model.Token;
import com.orm.v_1.SimpleDocumentMapper.interpreter.model.MethodMetadata;

public interface SemanticProvider {
	
	public MethodMetadata processing (List<Token> tokens, Parameter[] methodParameters, String methodName);

}
