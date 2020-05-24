package com.uoc.tfg.model.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;


public class Conf {
	@JsonProperty
	@SerializedName("conferenceteamstandings")
	private ConferenceTeamStandings conferenceteamstandings;
	
	
	public ConferenceTeamStandings getConferenceTeamStandings() {
		return conferenceteamstandings;
	}
	public void setConferenceTeamStandings(ConferenceTeamStandings conferenceteamstandings) {
		this.conferenceteamstandings = conferenceteamstandings;
	}
	public Conf() {
		
	}
	public Conf(ConferenceTeamStandings conferenceteamstandings) {
		this.conferenceteamstandings = conferenceteamstandings;
	}
	
}
