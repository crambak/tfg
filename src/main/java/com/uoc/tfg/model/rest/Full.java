package com.uoc.tfg.model.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;


public class Full {
	@JsonProperty
	@SerializedName("fullgameschedule")
	private Fullgameschedule fullgameschedule;
	
	
	public Fullgameschedule getFullgameschedule() {
		return fullgameschedule;
	}
	public void setFullgameschedule(Fullgameschedule fullgameschedule) {
		this.fullgameschedule = fullgameschedule;
	}
	public Full() {
		
	}
	public Full(Fullgameschedule fullgameschedule) {
		this.fullgameschedule = fullgameschedule;
	}
	
}
