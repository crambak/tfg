package com.uoc.tfg.model.bd;

public class Rating {

	private int id;
	private int idStatistics;
	private String name;
	private String description;
	private int days;
	private int numberMatchs;
	private String location;
	private boolean active;
	private boolean automatic;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdStatistics() {
		return idStatistics;
	}
	public void setIdStatistics(int idStatistics) {
		this.idStatistics = idStatistics;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public int getNumberMatchs() {
		return numberMatchs;
	}
	public void setNumberMatchs(int numberMatchs) {
		this.numberMatchs = numberMatchs;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isAutomatic() {
		return automatic;
	}
	public void setAutomatic(boolean automatic) {
		this.automatic = automatic;
	}
	public Rating(int id, int idStatistics, String name, String description, int days, int numberMatchs, String location, boolean active, boolean automatic) {
		this.id = id;
		this.idStatistics = idStatistics;
		this.name = name;
		this.description = description;
		this.days = days;
		this.numberMatchs = numberMatchs;
		this.location = location;
		this.active = active;
		this.automatic = automatic;
	}
	public Rating() {
	
	}

}
