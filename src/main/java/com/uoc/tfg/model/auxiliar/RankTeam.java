package com.uoc.tfg.model.auxiliar;

public class RankTeam {

	private int idTeam;
	private String name;
	private String rank;
	private float avg;
	public int getIdTeam() {
		return idTeam;
	}
	public void setIdTeam(int idTeam) {
		this.idTeam = idTeam;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public float getAvg() {
		return avg;
	}
	public void setAvg(float avg) {
		this.avg = avg;
	}
	public RankTeam(int idTeam, String name, String rank, float avg) {
		this.idTeam = idTeam;
		this.name = name;
		this.rank = rank;
		this.avg = avg;
	}
	public RankTeam() {
		
	}
	
}
