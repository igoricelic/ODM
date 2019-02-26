package com.orm.v_1.SimpleDocumentMapper.test.example2;

import java.util.List;

import com.orm.v_1.SimpleDocumentMapper.odm.config.IndigoConfigurator;
import com.orm.v_1.SimpleDocumentMapper.odm.config.impl.IndigoConfiguratorImpl;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.Specification;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.builders.SpecificationBuilder;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.enums.Comparator;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.enums.Operator;
import com.orm.v_1.SimpleDocumentMapper.repositories.SpecificationRepository;

public class MainTestSpecification {

	public static void main(String[] args) {
		try {
			IndigoConfigurator indigoConfigurator = new IndigoConfiguratorImpl("localhost", 27017, "test",
					List.of(Contact.class, Address.class, City.class, Resource.class));

			SpecificationRepository<Contact> contactDao = indigoConfigurator.provideSpecificationRepository(Contact.class);
			
			Specification<Contact> specification = new SpecificationBuilder<Contact>()
					.addCriterion("firstName", "Igor", Comparator.Equality)
					.addCriterion("age", 30, Comparator.LessThan).operator(Operator.Or).build();
			
			System.out.println(contactDao.count());
			
			List<Contact> contacts = contactDao.readBy(specification);
			for(Contact contact: contacts) {
				System.out.println(contact);
			}
					
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
