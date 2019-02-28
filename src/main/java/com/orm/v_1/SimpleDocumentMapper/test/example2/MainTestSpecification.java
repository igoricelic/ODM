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
			
//			Specification<Contact> specification = new SpecificationBuilder<Contact>()
//					.addCriterion("firstName", "Igor", Comparator.Equality)
//					.addCriterion("firstName", "Pera", Comparator.Equality).operator(Operator.Or).build();
			
//			Specification<Contact> specification = new SpecificationBuilder<Contact>()
//					.addCriterion("address.street", "Bulevar Mihajla Pupina", Comparator.Equality).build();
			
//			Specification<Contact> specification = new SpecificationBuilder<Contact>()
//					.addCriterion("address.city.postCode", 32240, Comparator.Equality).build();
			
//			Specification<Contact> specification = new SpecificationBuilder<Contact>()
//					.addCriterion("resources.value", "0638862088", Comparator.Equality).build();
			
//			Specification<Contact> specification = new SpecificationBuilder<Contact>()
//					.addCriterion("lastName", "Icelic", Comparator.Equality)
//					.addCriterion("address.city.name", "Lucani", Comparator.Equality).operator(Operator.Or).build();
			
//			Specification<Contact> specification = new SpecificationBuilder<Contact>()
//					.addCriterion("firstName", "Ig", Comparator.StartsWith).build();
			
//			Specification<Contact> specification = new SpecificationBuilder<Contact>()
//					.addCriterion("firstName", "go", Comparator.Contains).build();

			Specification<Contact> specification = new SpecificationBuilder<Contact>()
					.addCriterion("firstName", "or", Comparator.EndsWith).build();
			
			System.out.println(contactDao.count());
			
			List<Contact> contacts = contactDao.readBy(specification);
//			List<Contact> contacts = contactDao.readAll();
			for(Contact contact: contacts) {
				System.out.println(contact);
			}
					
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
