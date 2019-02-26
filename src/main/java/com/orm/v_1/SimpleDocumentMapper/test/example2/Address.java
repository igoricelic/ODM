package com.orm.v_1.SimpleDocumentMapper.test.example2;

import com.orm.v_1.SimpleDocumentMapper.model.annotations.Document;
import com.orm.v_1.SimpleDocumentMapper.model.annotations.Embedded;
import com.orm.v_1.SimpleDocumentMapper.model.annotations.Field;

@Document(embedded = true)
public class Address {
	
	private String street;
	
	@Field(name = "house_number")
	private Integer houseNumber;
	
	@Embedded(name = "city", targetEntity = City.class)
	private City city;
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public Integer getHouseNumber() {
		return houseNumber;
	}
	
	public void setHouseNumber(Integer houseNumber) {
		this.houseNumber = houseNumber;
	}
	
	public City getCity() {
		return city;
	}
	
	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Address [street=" + street + ", houseNumber=" + houseNumber + ", city=" + city + "]";
	}

}
