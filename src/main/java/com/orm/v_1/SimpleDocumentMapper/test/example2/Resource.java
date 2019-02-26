package com.orm.v_1.SimpleDocumentMapper.test.example2;

import java.util.List;

import com.orm.v_1.SimpleDocumentMapper.model.annotations.Document;

@Document(embedded = true)
public class Resource {
	
	private String type;
	
	private String value;
	
	private List<String> tags;
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public List<String> getTags() {
		return tags;
	}
	
	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Resource [type=" + type + ", value=" + value + ", tags=" + tags + "]";
	}

}
