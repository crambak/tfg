package com.uoc.tfg.model.auxiliar;

public class MatchTeam {

	private int id;
	private String name;
	private String abbreviation;
	private String balance;
	private String position;
	
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
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public MatchTeam(int id, String name, String abbreviation, String balance, String position) {
		this.id = id;
		this.name = name;
		this.abbreviation = abbreviation;
		this.balance = balance;
		this.position = position;
	}
	public MatchTeam() {
		
	}
	
}
