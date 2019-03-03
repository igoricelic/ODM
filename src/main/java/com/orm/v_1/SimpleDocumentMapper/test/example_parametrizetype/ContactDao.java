package com.orm.v_1.SimpleDocumentMapper.test.example_parametrizetype;

import java.util.List;
import java.util.Optional;

import com.orm.v_1.SimpleDocumentMapper.odm.structures.Page;
import com.orm.v_1.SimpleDocumentMapper.odm.structures.PageRequest;
import com.orm.v_1.SimpleDocumentMapper.test.example2.Contact;

public interface ContactDao {
	
	Contact readByFirstNameEq (String firstName);
	
	Optional<Contact> readByFirstNameEqAndLastNameEq (String firstName, String lastName);

	List<Contact> readByAddress_HouseNumberEq (Integer houseNumber);
	
	Optional<List<Contact>> readByLastNameEq (String lastName);
	
	Page<Contact> readByLastNameStartWith (String lastName, PageRequest pageRequest);
	
	Optional<Page<Contact>> readByAddress_StreetEqOrAddress_City_NameEq (String street, String name);
	
}
