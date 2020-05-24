package com.uoc.tfg.model.rest;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;


public class Conference {
	@JsonProperty
	@SerializedName("teamentry")
	private List<TeamEntry> teamentry;
	
	
	public List<TeamEntry> getTeamentry() {
		return teamentry;
	}
	public void setTeamentry(List<TeamEntry> teamentry) {
		this.teamentry = teamentry;
	}
	public Conference() {
		
	}
	public Conference(List<TeamEntry> teamentry) {
		this.teamentry = teamentry;
	}
	
}
