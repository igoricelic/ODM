package com.orm.v_1.SimpleDocumentMapper.test.example.dao_proxy;

import java.util.List;
import java.util.Optional;

import com.orm.v_1.SimpleDocumentMapper.repositories.PagingAndSortingRepository;
import com.orm.v_1.SimpleDocumentMapper.test.example2.Contact;

public interface ContactDao extends PagingAndSortingRepository<Contact> {
	
	public Optional<List<Contact>> readByFavoriteNumbersIn (Integer number);
	
	public List<Contact> readByFirstNameStartsWithAndLastNameContains (String firstName, String lastName);
	
	public List<Contact> readByResources_ValueEq (String value);
	
	public Optional<List<Contact>> readByAddress_City_NameEqAndAddress_StreetEq (String cityName, String street);
	
	public List<Contact> readByAgeLte (Integer age);

}
