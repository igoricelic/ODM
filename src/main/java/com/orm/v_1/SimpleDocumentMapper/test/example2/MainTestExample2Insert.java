package com.orm.v_1.SimpleDocumentMapper.test.example2;

import java.util.List;
import java.util.Optional;

import com.orm.v_1.SimpleDocumentMapper.odm.config.IndigoConfigurator;
import com.orm.v_1.SimpleDocumentMapper.odm.config.impl.IndigoConfiguratorImpl;
import com.orm.v_1.SimpleDocumentMapper.repositories.CrudRepository;

public class MainTestExample2Insert {
	
	public static void main(String[] args) {
		try {
			
			IndigoConfigurator indigoConfigurator = new IndigoConfiguratorImpl("localhost", 27017, "test", List.of(Contact.class, Address.class, City.class, Resource.class));
			
			CrudRepository<Contact> contactDao = indigoConfigurator.provideCrudRepository(Contact.class);
			
			System.out.println(contactDao.count());
			
			Optional<Contact> c1 = contactDao.readById("5c73c7bc40c9f90b5edbd4bf");
			Contact contact = c1.get();
			Resource r = new Resource();
			r.setType("NEW");
			r.setValue("TEST");
			r.setTags(List.of("FIRST", "SECOND"));
			contact.getResources().add(r);
			contact.setLastName("Icelic (Icela)");
			contactDao.updateOne(contact);
			
//			Optional<Contact> c2 = contactDao.readById("5c73c7bc40d9f90b5edbd4af");
//			System.out.println(c2);
			
//			Resource r1 = new Resource();
//			r1.setType("PHONE");
//			r1.setValue("0652512996");
//			r1.setTags(List.of("Igoja", "Igor", "Icelic"));
//			Resource r2 = new Resource();
//			r2.setType("EMAIL");
//			r2.setValue("igoricelic@outlook.com");
//			r2.setTags(List.of("Igoja", "Igor", "Icelic"));
//			Resource r3 = new Resource();
//			r3.setType("ADDRESS");
//			r3.setValue("Omladinska 10");
//			r3.setTags(List.of("Lucani", "Stara Kolonija", "LucaniUSrcu"));
//			
//			City city = new City();
//			city.setName("Lucani");
//			city.setPostCode(32240);
//			
//			Address address = new Address();
//			address.setCity(city);
//			address.setStreet("Omladinska");
//			address.setHouseNumber(10);
//			
//			Contact contact = new Contact();
//			contact.setAge(22);
//			contact.setDateOfBirth(new Date());
//			contact.setFirstName("Igor");
//			contact.setLastName("Icelic");
//			contact.setAddress(address);
//			contact.setResources(List.of(r1, r2, r3));
//			contact.setFavoriteNumbers(List.of(2, 5, 7, 12, 14));
//			contact.setPerformance(List.of("Igoja"));
//			
//			contactDao.createOne(contact);
//			
//			List<Contact> contacts = contactDao.readAll();
//			for(Contact c: contacts) {
//				System.out.println(c.toString());
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
