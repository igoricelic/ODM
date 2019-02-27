package com.orm.v_1.SimpleDocumentMapper.test.example2;

import java.util.List;

import com.orm.v_1.SimpleDocumentMapper.odm.config.IndigoConfigurator;
import com.orm.v_1.SimpleDocumentMapper.odm.config.impl.IndigoConfiguratorImpl;
import com.orm.v_1.SimpleDocumentMapper.odm.structures.Page;
import com.orm.v_1.SimpleDocumentMapper.odm.structures.PageRequest;
import com.orm.v_1.SimpleDocumentMapper.repositories.PagingAndSortingRepository;

public class MainTestPagination {
	
	public static void main(String[] args) {
		try {
			IndigoConfigurator indigoConfigurator = new IndigoConfiguratorImpl("localhost", 27017, "test", List.of(Contact.class, Address.class, City.class, Resource.class));
			
			PagingAndSortingRepository<Contact> contactDao = indigoConfigurator.providePagingAndSortingRepository(Contact.class);
			
			Page<Contact> pageContact = contactDao.readAll(new PageRequest(1, 1));
			for(Contact contact: pageContact.getContent()) {
				System.out.println(contact.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
