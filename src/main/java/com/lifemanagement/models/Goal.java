package com.lifemanagement.models;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "goals")
public class Goal {
	@Id
	String _id;
	String name;
	Date due;
	String content;
	String motivation;
	String lifearea;
	String category;
	String timehorizont;
	ArrayList<Goal> subgoals;
	Date created;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDue() {
		return due;
	}
	public void setDue(Date due) {
		this.due = due;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMotivation() {
		return motivation;
	}
	public void setMotivation(String motivation) {
		this.motivation = motivation;
	}
	public String getLifearea() {
		return lifearea;
	}
	public void setLifearea(String lifearea) {
		this.lifearea = lifearea;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTimehorizont() {
		return timehorizont;
	}
	public void setTimehorizont(String timehorizont) {
		this.timehorizont = timehorizont;
	}
	public ArrayList<Goal> getSubgoals() {
		return subgoals;
	}
	public void setSubgoals(ArrayList<Goal> subgoals) {
		this.subgoals = subgoals;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	

}
