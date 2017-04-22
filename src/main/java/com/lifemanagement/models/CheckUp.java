package com.lifemanagement.models;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "checkUpTemplates")
public class CheckUp {
	@Id
	String _id;
	String category;
	Date created;
	ArrayList<Question> questionTemplates;
	ArrayList<ToDo> todos;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public ArrayList<Question> getQuestionTemplates() {
		return questionTemplates;
	}
	public void setQuestionTemplates(ArrayList<Question> questionTemplates) {
		this.questionTemplates = questionTemplates;
	}
	public ArrayList<ToDo> getTodos() {
		return todos;
	}
	public void setTodos(ArrayList<ToDo> todos) {
		this.todos = todos;
	}
	
	
	

}
