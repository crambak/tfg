package com.uoc.tfg.model.auxiliar;

public class Standing {

	private int position;
	private String conference;
	private int idTeam;
	private String teamName;
	private int wins;
	private int loses;
	
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getConference() {
		return conference;
	}
	public void setConference(String conference) {
		this.conference = conference;
	}
	public int getIdTeam() {
		return idTeam;
	}
	public void setIdTeam(int idTeam) {
		this.idTeam = idTeam;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public int getLoses() {
		return loses;
	}
	public void setLoses(int loses) {
		this.loses = loses;
	}
	public Standing(int position, int idTeam, String teamName, int wins, int loses) {
		this.position = position;
		this.idTeam = idTeam;
		this.teamName = teamName;
		this.wins = wins;
		this.loses = loses;
	}
	public Standing() {
		
	}
	
}
