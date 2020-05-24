package com.uoc.tfg.model.auxiliar;

public class MatchReboundsStats {

	private int idTeam;
	private String team;
	private int offensive;
	private int defensive;
	private int total;
	
	public int getIdTeam() {
		return idTeam;
	}
	public void setIdTeam(int idTeam) {
		this.idTeam = idTeam;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public int getOffensive() {
		return offensive;
	}
	public void setOffensive(int offensive) {
		this.offensive = offensive;
	}
	public int getDefensive() {
		return defensive;
	}
	public void setDefensive(int defensive) {
		this.defensive = defensive;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public MatchReboundsStats(int idTeam, String team, int offensive, int defensive, int total) {
		this.idTeam = idTeam;
		this.team = team;
		this.offensive = offensive;
		this.defensive = defensive;
		this.total = total;
	}
	public MatchReboundsStats() {
		
	}
	
}
