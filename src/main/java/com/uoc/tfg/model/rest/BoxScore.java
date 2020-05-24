package com.uoc.tfg.model.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;


public class BoxScore {
	@JsonProperty
	@SerializedName("gameboxscore")
	private GameBoxScore gameboxscore;
	public BoxScore() {
		
	}
	public GameBoxScore getGameboxscore() {
		return gameboxscore;
	}
	public void setGameboxscore(GameBoxScore gameboxscore) {
		this.gameboxscore = gameboxscore;
	}
	public BoxScore(GameBoxScore gameboxscore) {
		this.gameboxscore = gameboxscore;
	}
	
}
