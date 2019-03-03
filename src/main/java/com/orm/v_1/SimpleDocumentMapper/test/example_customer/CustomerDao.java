package com.orm.v_1.SimpleDocumentMapper.test.example_customer;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.orm.v_1.SimpleDocumentMapper.repositories.PagingAndSortingRepository;

public interface CustomerDao extends PagingAndSortingRepository<Customer> {
	
	public boolean existsByEmailEq (String email);
	
	public Optional<Customer> readByEmailEqAndPasswordEq (String email, String password);
	
	public List<Customer> readByPackages_TitleStartsWith (String packageTitlePrefix);
	
	public List<Customer> readByLastNameEndWithAndPackages_CreatedAtEq (String lastName, Date createdAt);

}
