package com.orm.v_1.SimpleDocumentMapper.test.example.dao_proxy;

import java.util.List;
import java.util.Optional;
import java.util.Random;

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
			
			ContactDao contactDao = (ContactDao) indigoConfigurator.provideProxy(ContactDao.class, Contact.class);
			
//			Optional<Contact> optionalContact = contactDao.readById("5c72dbeaad87d31de01b59c5");
//			if(optionalContact.isPresent()) {
//				Contact contact = optionalContact.get();
//				contact.setFirstName("Igorko");
//				contactDao.updateOne(contact);
//			}
			
//			Resource resource = new Resource();
//			resource.setType("SKYPE");
//			resource.setValue("my_test_channel");
//			resource.setTags(List.of("#CAM", "#LIVE_CHAT", "#CHILL"));
//			Random random = new Random();
//			List<Contact> contacts = contactDao.readAll();
//			for(Contact contact: contacts) {
//				contact.getResources().add(resource);
//				int position = random.nextInt(contact.getFavoriteNumbers().size());
//				System.out.println("Iz contacta: "+contact.getFirstName()+" obrisao sam broj "+contact.getFavoriteNumbers().get(position));
//				contact.getFavoriteNumbers().remove(position);
//			}
//			contactDao.updateMore(contacts);
			
			System.out.println(contactDao.count());
			
			List<Contact> contacts = contactDao.readAll();
			for(Contact contact: contacts) {
				System.out.println(contact.toString());
			}
			
			Optional<List<Contact>> whoLove61 = contactDao.readByFavoriteNumbersIn(61);
			for(Contact contact: whoLove61.get()) {
				System.out.println(contact.toString());
			}
			
			List<Contact> firstNameStartLastNameContainsContacts = contactDao.readByFirstNameStartsWithAndLastNameContains("Igo", "lia");
			for(Contact contact: firstNameStartLastNameContainsContacts) {
				System.out.println(contact.toString());
			}
			
			List<Contact> resourceValueContainsNumber = contactDao.readByResources_ValueEq("0638862088");
			for(Contact contact: resourceValueContainsNumber) {
				System.out.println(contact.toString());
			}
			
			Optional<List<Contact>> addressStreetCity = contactDao.readByAddress_City_NameEqAndAddress_StreetEq("Lucani", "Omladinska");
			for(Contact contact: addressStreetCity.get()) {
				System.out.println(contact.toString());
			}
			
			List<Contact> contactsLtsAge = contactDao.readByAgeLte(32);
			for(Contact contact: contactsLtsAge) {
				System.out.println(contact.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
