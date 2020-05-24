package com.uoc.tfg.model.rest;

import com.google.gson.annotations.SerializedName;

public class GameEntry {

	@SerializedName("id")
	private int id;
	@SerializedName("date")
	private String date;
	@SerializedName("time")
	private String time;
	@SerializedName("awayTeam")
	private Team awayTeam;
	@SerializedName("homeTeam")
	private Team homeTeam;
	@SerializedName("location")
	private String location;
	
	public GameEntry() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Team getAwayTeam() {
		return awayTeam;
	}
	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}
	public Team getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public GameEntry(int id, String date, String time, Team awayTeam, Team homeTeam, String location) {
		this.id = id;
		this.date = date;
		this.time = time;
		this.awayTeam = awayTeam;
		this.homeTeam = homeTeam;
		this.location = location;
	}
}
