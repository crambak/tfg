package com.uoc.tfg.model.bd;

import java.util.Date;

public class Match {

	private int id;
	private int idAwayTeam;
	private int idHomeTeam;
	private Date date;
	private String season;
	private String location;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdAwayTeam() {
		return idAwayTeam;
	}
	public void setIdAwayTeam(int idAwayTeam) {
		this.idAwayTeam = idAwayTeam;
	}
	public int getIdHomeTeam() {
		return idHomeTeam;
	}
	public void setIdHomeTeam(int idHomeTeam) {
		this.idHomeTeam = idHomeTeam;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Match(int id, int idAwayTeam, int idHomeTeam, Date date, String season, String location) {
		this.id = id;
		this.idAwayTeam = idAwayTeam;
		this.idHomeTeam = idHomeTeam;
		this.date = date;
		this.season = season;
		this.location = location;
	}
	public Match() {
		
	}
	
}
