package com.orm.v_1.SimpleDocumentMapper.test.example_customer;

import java.util.Date;
import java.util.List;

import com.orm.v_1.SimpleDocumentMapper.model.annotations.Document;
import com.orm.v_1.SimpleDocumentMapper.model.annotations.Field;

@Document(embedded = true)
public class Package {
	
	private String title;
	
	@Field(name = "created_at")
	private Date createdAt;
	
	private List<String> channels;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public List<String> getChannels() {
		return channels;
	}
	
	public void setChannels(List<String> channels) {
		this.channels = channels;
	}

	@Override
	public String toString() {
		return "Package [title=" + title + ", createdAt=" + createdAt + ", channels=" + channels + "]";
	}

}
