package com.orm.v_1.SimpleDocumentMapper.test.example2;

import com.orm.v_1.SimpleDocumentMapper.model.annotations.Document;
import com.orm.v_1.SimpleDocumentMapper.model.annotations.Field;

@Document(embedded = true)
public class City {
	
	private String name;
	
	@Field(name = "post_code")
	private Integer postCode;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getPostCode() {
		return postCode;
	}
	
	public void setPostCode(Integer postCode) {
		this.postCode = postCode;
	}

	@Override
	public String toString() {
		return "City [name=" + name + ", postCode=" + postCode + "]";
	}

}
