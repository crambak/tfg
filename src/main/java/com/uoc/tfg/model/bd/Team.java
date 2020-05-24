package com.uoc.tfg.model.bd;

public class Team {

	private int id;
	private String name;
	private String city;
	private String abbreviation;
	private String conference;
	private int loses;
	private int wins;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public String getConference() {
		return conference;
	}
	public void setConference(String conference) {
		this.conference = conference;
	}
	public int getLoses() {
		return loses;
	}
	public void setLoses(int loses) {
		this.loses = loses;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}

	public Team(int id, String name, String city, String abbreviation, String conference, int loses, int wins) {
		this.id = id;
		this.name = name;
		this.city = city;
		this.abbreviation = abbreviation;
		this.conference = conference;
		this.loses = loses;
		this.wins = wins;
	}
	public Team() {
		
	}
}
