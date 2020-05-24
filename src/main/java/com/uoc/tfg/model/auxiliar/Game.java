package com.uoc.tfg.model.auxiliar;

public class Game {

	private int id;
	private int idAwayTeam;
	private int idHomeTeam;
	private String awayTeamName;
	private String homeTeamName;
	private String result;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdAwayTeam() {
		return idAwayTeam;
	}
	public void setIdAwayTeam(int idAwayTeam) {
		this.idAwayTeam = idAwayTeam;
	}
	public int getIdHomeTeam() {
		return idHomeTeam;
	}
	public void setIdHomeTeam(int idHomeTeam) {
		this.idHomeTeam = idHomeTeam;
	}
	public String getAwayTeamName() {
		return awayTeamName;
	}
	public void setAwayTeamName(String awayTeamName) {
		this.awayTeamName = awayTeamName;
	}
	public String getHomeTeamName() {
		return homeTeamName;
	}
	public void setHomeTeamName(String homeTeamName) {
		this.homeTeamName = homeTeamName;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Game(int id, int idAwayTeam, int idHomeTeam, String awayTeamName, String homeTeamName, String result) {
		this.id = id;
		this.idAwayTeam = idAwayTeam;
		this.idHomeTeam = idHomeTeam;
		this.awayTeamName = awayTeamName;
		this.homeTeamName = homeTeamName;
		this.result = result;
	}
	public Game() {
		
	}
	
}
