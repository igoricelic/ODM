package com.orm.v_1.SimpleDocumentMapper.test.example2;

import java.util.List;

import com.orm.v_1.SimpleDocumentMapper.odm.config.IndigoConfigurator;
import com.orm.v_1.SimpleDocumentMapper.odm.config.impl.IndigoConfiguratorImpl;
import com.orm.v_1.SimpleDocumentMapper.repositories.CrudRepository;

public class MainTestExample2 {
	
	public static void main(String[] args) {
		try {
			IndigoConfigurator indigoConfigurator = new IndigoConfiguratorImpl("localhost", 27017, "test", List.of(Contact.class, Address.class, City.class, Resource.class));
			
			CrudRepository<Contact> contactDao = indigoConfigurator.provideCrudRepository(Contact.class);
			
			System.out.println(contactDao.count());
			
			List<Contact> contacts = contactDao.readAll();
			for(Contact contact: contacts) {
				System.out.println(contact.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
