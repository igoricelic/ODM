package com.orm.v_1.SimpleDocumentMapper.test.entities;

import com.orm.v_1.SimpleDocumentMapper.model.annotations.Document;

@Document(embedded = true)
public class Car {
	
	private String model;
	
	private String maker;
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getMaker() {
		return maker;
	}
	
	public void setMaker(String maker) {
		this.maker = maker;
	}

	@Override
	public String toString() {
		return "Car [model=" + model + ", maker=" + maker + "]";
	}

}
