package com.uoc.tfg.model.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;


public class GameBoxScore {
	@JsonProperty
	@SerializedName("lastUpdatedOn")
	private String lastUpdatedOn;
	@JsonProperty
	@SerializedName("game")
	private Game game;
	@SerializedName("quarterSummary")
	private QuarterSummary quarterSummary;
	@SerializedName("awayTeam")
	private AwayTeam awayTeam;
	@SerializedName("homeTeam")
	private HomeTeam homeTeam;
	public GameBoxScore() {
	}
	public String getLastUpdatedOn() {
		return lastUpdatedOn;
	}
	public void setLastUpdatedOn(String lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public QuarterSummary getQuarterSummary() {
		return quarterSummary;
	}
	public void setQuarterSummary(QuarterSummary quarterSummary) {
		this.quarterSummary = quarterSummary;
	}
	public AwayTeam getAwayTeam() {
		return awayTeam;
	}
	public void setAwayTeam(AwayTeam awayTeam) {
		this.awayTeam = awayTeam;
	}
	public HomeTeam getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(HomeTeam homeTeam) {
		this.homeTeam = homeTeam;
	}
	public GameBoxScore(String lastUpdatedOn, Game game, QuarterSummary quarterSummary, AwayTeam awayTeam, HomeTeam homeTeam) {
		this.lastUpdatedOn = lastUpdatedOn;
		this.game = game;
		this.quarterSummary = quarterSummary;
		this.awayTeam = awayTeam;
		this.homeTeam = homeTeam;
	}
	
}
