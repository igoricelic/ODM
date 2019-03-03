package com.orm.v_1.SimpleDocumentMapper.test.example_parametrizetype;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.conversions.Bson;

import com.orm.v_1.SimpleDocumentMapper.interpreter.InterpreterProvider;
import com.orm.v_1.SimpleDocumentMapper.interpreter.InterpreterProviderImpl;
import com.orm.v_1.SimpleDocumentMapper.interpreter.model.MethodMetadata;
import com.orm.v_1.SimpleDocumentMapper.model.MDatabase;
import com.orm.v_1.SimpleDocumentMapper.model.MDocument;
import com.orm.v_1.SimpleDocumentMapper.odm.config.IndigoConfigurator;
import com.orm.v_1.SimpleDocumentMapper.odm.config.impl.IndigoConfiguratorImpl;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.SpecificationResolver;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.impl.SpecificationResolverImpl;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.Specification;
import com.orm.v_1.SimpleDocumentMapper.test.example2.Address;
import com.orm.v_1.SimpleDocumentMapper.test.example2.City;
import com.orm.v_1.SimpleDocumentMapper.test.example2.Contact;
import com.orm.v_1.SimpleDocumentMapper.test.example2.Resource;

public class Test4 {

	public static void main(String[] args) {
		try {
			IndigoConfigurator indigoConfigurator = new IndigoConfiguratorImpl("localhost", 27017, "test",
					List.of(Contact.class, Address.class, City.class, Resource.class));

			MDatabase databaseMetadata = indigoConfigurator.provideModel();
			MDocument documentMetadata = databaseMetadata.getDocumentMetadata(Contact.class).get();
			
			InterpreterProvider interpreterProvider = new InterpreterProviderImpl();
			SpecificationResolver specificationResolver = new SpecificationResolverImpl(documentMetadata);
			
			Map<String, Object[]> gridData = new HashMap<String, Object[]>();
			gridData.put("readByFirstNameEq", new Object[]{"Igor"});
			gridData.put("readByFirstNameEqAndLastNameEq", new Object[]{"Igor", "Icelic"});
			gridData.put("readByAddress_HouseNumberEq", new Object[]{5});
			gridData.put("readByLastNameEq", new Object[]{"Prezime"});
			gridData.put("readByLastNameStartWith", new Object[]{true});
			gridData.put("readByAddress_StreetEqOrAddress_City_NameEq", new Object[]{"Omladinska 10", 15});
			
			Class<?> c = ContactDao.class;
			Method[] allMethods = c.getDeclaredMethods();
			for(Method method: allMethods) {
				System.out.println("======================");
				System.out.println(method.toGenericString());
				MethodMetadata methodMetadata = interpreterProvider.interpretMethod(method, documentMetadata);
				Object[] argsGrid = gridData.get(method.getName());
				Specification<Contact> spec = specificationResolver.prepareSpecificaiton(methodMetadata.getSpecificationProposal(), argsGrid);
				Bson bson = specificationResolver.processing(spec);
				System.out.println(bson);
				System.out.println("======================");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
