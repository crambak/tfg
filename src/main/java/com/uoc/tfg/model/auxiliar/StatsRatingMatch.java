package com.uoc.tfg.model.auxiliar;

public class StatsRatingMatch {

	private String location;
	private String opp;
	private String date;
	private float teamStat;
	private float oppStat;
	private float sumStat;
	private String colourTeam;
	private String colourOpp;
	private String colourSum;
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getOpp() {
		return opp;
	}
	public void setOpp(String opp) {
		this.opp = opp;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public float getTeamStat() {
		return teamStat;
	}
	public void setTeamStat(float teamStat) {
		this.teamStat = teamStat;
	}
	public float getOppStat() {
		return oppStat;
	}
	public void setOppStat(float oppStat) {
		this.oppStat = oppStat;
	}
	public float getSumStat() {
		return sumStat;
	}
	public void setSumStat(float sumStat) {
		this.sumStat = sumStat;
	}
	public String getColourTeam() {
		return colourTeam;
	}
	public void setColourTeam(String colourTeam) {
		this.colourTeam = colourTeam;
	}
	public String getColourOpp() {
		return colourOpp;
	}
	public void setColourOpp(String colourOpp) {
		this.colourOpp = colourOpp;
	}
	public String getColourSum() {
		return colourSum;
	}
	public void setColourSum(String colourSum) {
		this.colourSum = colourSum;
	}
	public StatsRatingMatch(String location, String opp, String date, float teamStat, float oppStat,  float sumStat, String colourTeam, String colourOpp, String colourSum) {
		this.location = location;
		this.opp = opp;
		this.date = date;
		this.teamStat = teamStat;
		this.oppStat = oppStat;
		this.sumStat = sumStat;
		this.colourTeam = colourTeam;
		this.colourOpp = colourOpp;
		this.colourSum = colourSum;
	}
	public StatsRatingMatch() {
		
	}
	
}
