package com.orm.v_1.SimpleDocumentMapper.test.entities;

import com.orm.v_1.SimpleDocumentMapper.model.annotations.Document;
import com.orm.v_1.SimpleDocumentMapper.model.annotations.Embedded;
import com.orm.v_1.SimpleDocumentMapper.model.annotations.Field;

@Document(embedded = true)
public class Address {
	
	private String city;
	
	@Field(name = "postCode")
	private Long postNumber;
	
	private String street;
	
	private Long houseNumber;
	
	@Embedded
	private Car car;
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public Long getPostNumber() {
		return postNumber;
	}
	
	public void setPostNumber(Long postNumber) {
		this.postNumber = postNumber;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public Long getHouseNumber() {
		return houseNumber;
	}
	
	public void setHouseNumber(Long houseNumber) {
		this.houseNumber = houseNumber;
	}
	
	public Car getCar() {
		return car;
	}
	
	public void setCar(Car car) {
		this.car = car;
	}

	@Override
	public String toString() {
		return "Address [city=" + city + ", postNumber=" + postNumber + ", street=" + street + ", houseNumber="
				+ houseNumber + ", car=" + car + "]";
	}

}
