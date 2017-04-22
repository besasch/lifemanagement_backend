package com.lifemanagement.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "mindesets")
public class Mindset {
	@Id
	String _id;
	String oldMindset;
	String newMindset;
	String status;
	Date created;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getOldMindset() {
		return oldMindset;
	}
	public void setOldMindset(String oldMindset) {
		this.oldMindset = oldMindset;
	}
	public String getNewMindset() {
		return newMindset;
	}
	public void setNewMindset(String newMindset) {
		this.newMindset = newMindset;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
}
