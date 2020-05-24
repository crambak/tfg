package com.uoc.tfg.model.rest;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;


public class ConferenceTeamStandings {
	@JsonProperty
	@SerializedName("lastUpdatedOn")
	private String lastUpdatedOn;
	@JsonProperty
	@SerializedName("conference")
	private List<Conference> conference;
	
	public String getLastUpdatedOn() {
		return lastUpdatedOn;
	}
	public void setLastUpdatedOn(String lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}
	public List<Conference> getConference() {
		return conference;
	}
	public void setConference(List<Conference> conference) {
		this.conference = conference;
	}
	public ConferenceTeamStandings() {
		
	}
	public ConferenceTeamStandings(String lastUpdatedOn, List<Conference> conference) {
		this.lastUpdatedOn = lastUpdatedOn;
		this.conference = conference;
	}
	
}
