package com.uoc.tfg.model.rest;

import com.google.gson.annotations.SerializedName;

public class Game {
	@SerializedName("awayTeam")
	private Team awayTeam;
	@SerializedName("homeTeam")
	private Team homeTeam;
	
	public Game() {
		
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
	public Game(Team awayTeam, Team homeTeam) {
		this.awayTeam = awayTeam;
		this.homeTeam = homeTeam;
	}
	
}
