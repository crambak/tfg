package com.uoc.tfg.model.rest;

import com.google.gson.annotations.SerializedName;

public class Quarter {
	@SerializedName("awayScore")
	private int awayScore;
	@SerializedName("homeScore")
	private int homeScore;
	
	public Quarter() {
		
	}
	public int getAwayScore() {
		return awayScore;
	}
	public void setAwayScore(int awayScore) {
		this.awayScore = awayScore;
	}
	public int getHomeScore() {
		return homeScore;
	}
	public void setHomeScore(int homeScore) {
		this.homeScore = homeScore;
	}
	public Quarter(int awayScore, int homeScore) {
		this.awayScore = awayScore;
		this.homeScore = homeScore;
	}
	
}
