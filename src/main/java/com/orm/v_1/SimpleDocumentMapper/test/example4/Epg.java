package com.orm.v_1.SimpleDocumentMapper.test.example4;

import java.util.Date;

import com.orm.v_1.SimpleDocumentMapper.model.annotations.Document;
import com.orm.v_1.SimpleDocumentMapper.model.annotations.Embedded;
import com.orm.v_1.SimpleDocumentMapper.model.annotations.Id;

@Document(collection = "epg")
public class Epg {
	
	@Id
	private String id;
	
	private String title;
	
	private String originalTitle;
	
	private String description;
	
	private String type;
	
	private Date start;
	
	private Date end;
	
	@Embedded
	private Content content;
	
	private String channelName;
	
	private Boolean stored;
	
	private String recordingStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Boolean getStored() {
		return stored;
	}

	public void setStored(Boolean stored) {
		this.stored = stored;
	}

	public String getRecordingStatus() {
		return recordingStatus;
	}

	public void setRecordingStatus(String recordingStatus) {
		this.recordingStatus = recordingStatus;
	}

	@Override
	public String toString() {
		return "Epg [id=" + id + ", title=" + title + ", originalTitle=" + originalTitle + ", description="
				+ description + ", type=" + type + ", start=" + start + ", end=" + end + ", content=" + content
				+ ", channelName=" + channelName + ", stored=" + stored + ", recordingStatus=" + recordingStatus + "]";
	}

}
