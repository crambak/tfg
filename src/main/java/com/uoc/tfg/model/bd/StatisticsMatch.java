package com.uoc.tfg.model.bd;

public class StatisticsMatch {

	private int id;
	private int idMatch;
	private int idStatistics;
	private int awayTeam;
	private int homeTeam;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdMatch() {
		return idMatch;
	}
	public void setIdMatch(int idMatch) {
		this.idMatch = idMatch;
	}
	public int getIdStatistics() {
		return idStatistics;
	}
	public void setIdStatistics(int idStatistics) {
		this.idStatistics = idStatistics;
	}
	public int getAwayTeam() {
		return awayTeam;
	}
	public void setAwayTeam(int awayTeam) {
		this.awayTeam = awayTeam;
	}
	public int getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(int homeTeam) {
		this.homeTeam = homeTeam;
	}
	public StatisticsMatch(int id, int idMatch, int idStatistics, int awayTeam, int homeTeam) {
		this.id = id;
		this.idMatch = idMatch;
		this.idStatistics = idStatistics;
		this.awayTeam = awayTeam;
		this.homeTeam = homeTeam;
	}
	public StatisticsMatch() {
		
	}
}
