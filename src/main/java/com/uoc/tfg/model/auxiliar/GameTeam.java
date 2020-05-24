package com.uoc.tfg.model.auxiliar;

public class GameTeam {

	private int id;
	private String location;
	private String date;
	private int idOpponent;
	private String opponent;
	private String result;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getIdOpponent() {
		return idOpponent;
	}
	public void setIdOpponent(int idOpponent) {
		this.idOpponent = idOpponent;
	}
	public String getOpponent() {
		return opponent;
	}
	public void setOpponent(String opponent) {
		this.opponent = opponent;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public GameTeam(int id, String location, String date, int idOpponent, String opponent, String result) {
		this.id = id;
		this.location = location;
		this.date = date;
		this.idOpponent = idOpponent;
		this.opponent = opponent;
		this.result = result;
	}
	public GameTeam() {
		
	}
	
}
