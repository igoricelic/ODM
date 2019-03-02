package com.orm.v_1.SimpleDocumentMapper.interpreter;

import java.lang.reflect.Method;

import com.orm.v_1.SimpleDocumentMapper.interpreter.model.MethodMetadata;
import com.orm.v_1.SimpleDocumentMapper.model.MDocument;

public interface InterpreterProvider {
	
	public MethodMetadata interpretMethod (Method method, MDocument documentMetadata);

}
