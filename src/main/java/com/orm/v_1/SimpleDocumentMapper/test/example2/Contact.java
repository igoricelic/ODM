package com.orm.v_1.SimpleDocumentMapper.test.example2;

import java.util.Date;
import java.util.List;

import com.orm.v_1.SimpleDocumentMapper.model.annotations.Document;
import com.orm.v_1.SimpleDocumentMapper.model.annotations.Embedded;
import com.orm.v_1.SimpleDocumentMapper.model.annotations.Field;
import com.orm.v_1.SimpleDocumentMapper.model.annotations.Id;

@Document(collection = "contacts")
public class Contact {
	
	@Id
	private String id;
	
	@Field(name = "first_name")
	private String firstName;
	
	@Field(name = "last_name")
	private String lastName;
	
	@Field(name = "date_of_birth")
	private Date dateOfBirth;
	
	private Integer age;
	
	@Embedded
	private Address address;
	
	@Embedded
	private List<Resource> resources;
	
	private List<String> performance;
	
	@Field(name = "favorite_numbers")
	private List<Integer> favoriteNumbers;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public Integer getAge() {
		return age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public List<Resource> getResources() {
		return resources;
	}
	
	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
	
	public List<String> getPerformance() {
		return performance;
	}
	
	public void setPerformance(List<String> performance) {
		this.performance = performance;
	}
	
	public List<Integer> getFavoriteNumbers() {
		return favoriteNumbers;
	}
	
	public void setFavoriteNumbers(List<Integer> favoriteNumbers) {
		this.favoriteNumbers = favoriteNumbers;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth="
				+ dateOfBirth + ", age=" + age + ", address=" + address + ", resources=" + resources + ", performance="
				+ performance + ", favoriteNumbers=" + favoriteNumbers + "]";
	}

}
