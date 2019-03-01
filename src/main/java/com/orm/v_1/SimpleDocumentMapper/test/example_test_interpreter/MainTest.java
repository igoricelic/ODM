package com.orm.v_1.SimpleDocumentMapper.test.example_test_interpreter;

import java.util.List;

import com.orm.v_1.SimpleDocumentMapper.interpreter.lexer.LexerProvider;
import com.orm.v_1.SimpleDocumentMapper.interpreter.lexer.LexerProviderImpl;
import com.orm.v_1.SimpleDocumentMapper.interpreter.lexer.model.Token;
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
			
			String method1 = "readByFirstNameEq";
			List<Token> tokens1 = lexerProvider.processing(method1, documentMetadata);
			System.out.println(tokens1.size());
			
			String method2 = "readByFirstNameAndLastNameEq";
			List<Token> tokens2 = lexerProvider.processing(method2, documentMetadata);
			System.out.println(tokens2.size());
			
			String method3 = "readByAddress_HouseNumberEq";
			List<Token> tokens3 = lexerProvider.processing(method3, documentMetadata);
			System.out.println(tokens3.size());
			
			String method4 = "readByAddress_HouseNumberEqAndAddress_City_NameEq";
			List<Token> tokens4 = lexerProvider.processing(method4, documentMetadata);
			System.out.println(tokens4.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
