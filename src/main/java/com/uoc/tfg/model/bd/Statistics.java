package com.uoc.tfg.model.bd;

public class Statistics {

	private int id;
	private String type;
	private String description;
	private boolean active;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Statistics(int id, String type, String description, boolean active) {
		this.id = id;
		this.type = type;
		this.description = description;
		this.active = active;
	}
	public Statistics() {
		
	}
}
