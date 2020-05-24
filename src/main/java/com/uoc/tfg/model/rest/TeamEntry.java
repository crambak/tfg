package com.uoc.tfg.model.rest;

import com.google.gson.annotations.SerializedName;

public class TeamEntry {

	@SerializedName("team")
	private Team team;
	
	public TeamEntry() {
		
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public TeamEntry(Team team) {
		this.team = team;
	}
}
