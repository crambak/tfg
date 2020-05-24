package com.uoc.tfg.model.rest;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;


public class Fullgameschedule {
	@JsonProperty
	@SerializedName("lastUpdatedOn")
	private String lastUpdatedOn;
	@JsonProperty
	@SerializedName("gameentry")
	private List<GameEntry> gameentry;
	
	public String getLastUpdatedOn() {
		return lastUpdatedOn;
	}
	public void setLastUpdatedOn(String lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}
	public List<GameEntry> getGameentry() {
		return gameentry;
	}
	public void setGameentry(List<GameEntry> gameentry) {
		this.gameentry = gameentry;
	}
	public Fullgameschedule() {
		
	}
	public Fullgameschedule(String lastUpdatedOn, List<GameEntry> gameentry) {
		this.lastUpdatedOn = lastUpdatedOn;
		this.gameentry = gameentry;
	}
	
}
