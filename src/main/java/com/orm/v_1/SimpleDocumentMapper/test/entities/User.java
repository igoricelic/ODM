package com.orm.v_1.SimpleDocumentMapper.test.entities;

import java.util.List;

import com.orm.v_1.SimpleDocumentMapper.model.annotations.Document;
import com.orm.v_1.SimpleDocumentMapper.model.annotations.Embedded;
import com.orm.v_1.SimpleDocumentMapper.model.annotations.Id;

@Document(collection = "users")
public class User {
	
	@Id
	private String id;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private Integer role;
	
	private String index;
	
	private String grupa;
	
	@Embedded
	private Address address;
	
	@Embedded
	private List<Car> cars;
	
	public User() {
		super();
	}

	public User(String password, String firstName, String lastName, String email, Integer role, String index,
			String grupa) {
		super();
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.index = index;
		this.grupa = grupa;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getGrupa() {
		return grupa;
	}

	public void setGrupa(String grupa) {
		this.grupa = grupa;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public List<Car> getCars() {
		return cars;
	}
	
	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", role=" + role + ", index=" + index + ", grupa=" + grupa + ", address="
				+ address + ", cars=" + cars + "]";
	}

}
