package com.orm.v_1.SimpleDocumentMapper.test.example_parametrizetype;

import java.util.List;
import java.util.Optional;

import com.orm.v_1.SimpleDocumentMapper.test.example2.Contact;

public interface ContactDao {
	
	Contact readByFirstNameEq (String firstName);
	
	Optional<Contact> readByFirstNameEqAndLastNameEq (String firstName, String lastName);

	List<Contact> readAll ();
	
	Optional<List<Contact>> readByLastNameEq (String lastName);
	
}
