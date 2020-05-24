package com.uoc.tfg.model.rest;

import com.google.gson.annotations.SerializedName;

public class Team {
	@SerializedName("id")
	private int id;
	@SerializedName("city")
	private String city;
	@SerializedName("name")
	private String name;
	@SerializedName("abbreviation")
	private String abbreviation;
	
	public Team() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public Team(int ID, String City, String Name, String Abbreviation) {
		this.id = ID;
		this.city = City;
		this.name = Name;
		this.abbreviation = Abbreviation;
	}
	
}
