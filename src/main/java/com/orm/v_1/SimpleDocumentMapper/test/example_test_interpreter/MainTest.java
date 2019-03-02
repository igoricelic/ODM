package com.orm.v_1.SimpleDocumentMapper.test.example_test_interpreter;

import java.util.List;

import com.orm.v_1.SimpleDocumentMapper.interpreter.lexer.LexerProvider;
import com.orm.v_1.SimpleDocumentMapper.interpreter.lexer.LexerProviderImpl;
import com.orm.v_1.SimpleDocumentMapper.interpreter.lexer.model.Token;
import com.orm.v_1.SimpleDocumentMapper.interpreter.model.MethodMetadata;
import com.orm.v_1.SimpleDocumentMapper.interpreter.semantic.SemanticProvider;
import com.orm.v_1.SimpleDocumentMapper.interpreter.semantic.SemanticProviderImpl;
import com.orm.v_1.SimpleDocumentMapper.model.MDatabase;
import com.orm.v_1.SimpleDocumentMapper.model.MDocument;
import com.orm.v_1.SimpleDocumentMapper.odm.config.IndigoConfigurator;
import com.orm.v_1.SimpleDocumentMapper.odm.config.impl.IndigoConfiguratorImpl;
import com.orm.v_1.SimpleDocumentMapper.test.example2.Address;
import com.orm.v_1.SimpleDocumentMapper.test.example2.City;
import com.orm.v_1.SimpleDocumentMapper.test.example2.Contact;
import com.orm.v_1.SimpleDocumentMapper.test.example2.Resource;

public class MainTest {
	
	public static void main(String[] args) {
		try {
			IndigoConfigurator indigoConfigurator = new IndigoConfiguratorImpl("localhost", 27017, "test", List.of(Contact.class, Address.class, City.class, Resource.class));
			
			MDatabase databaseMetadata = indigoConfigurator.provideModel();
			MDocument documentMetadata = databaseMetadata.getDocumentMetadata(Contact.class).get();
			
			LexerProvider lexerProvider = new LexerProviderImpl();
			SemanticProvider semanticProvider = new SemanticProviderImpl();
			
			String method1 = "readByFirstNameEq";
			List<Token> tokens1 = lexerProvider.processing(method1, documentMetadata);
			System.out.println(tokens1.size());
			MethodMetadata methodMetadata1 = semanticProvider.processing(tokens1);
			System.out.println(methodMetadata1.getMethodPrefixType());
			
			String method2 = "readByFirstNameEqAndLastNameEq";
			List<Token> tokens2 = lexerProvider.processing(method2, documentMetadata);
			System.out.println(tokens2.size());
			MethodMetadata methodMetadata2 = semanticProvider.processing(tokens2);
			System.out.println(methodMetadata2.getMethodPrefixType());
			
			String method3 = "readByAddress_HouseNumberEq";
			List<Token> tokens3 = lexerProvider.processing(method3, documentMetadata);
			System.out.println(tokens3.size());
			MethodMetadata methodMetadata3 = semanticProvider.processing(tokens3);
			System.out.println(methodMetadata3.getMethodPrefixType());
			
			String method4 = "readByAddress_HouseNumberEqAndAddress_City_NameEq";
			List<Token> tokens4 = lexerProvider.processing(method4, documentMetadata);
			System.out.println(tokens4.size());
			MethodMetadata methodMetadata4 = semanticProvider.processing(tokens4);
			System.out.println(methodMetadata4.getMethodPrefixType());
			
			String method5 = "readByFirstNameEqOrAddress_HouseNumberEqAndLastNameEq";
			List<Token> tokens5 = lexerProvider.processing(method5, documentMetadata);
			System.out.println(tokens5.size());
			MethodMetadata methodMetadata5 = semanticProvider.processing(tokens5);
			System.out.println(methodMetadata5.getMethodPrefixType());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
