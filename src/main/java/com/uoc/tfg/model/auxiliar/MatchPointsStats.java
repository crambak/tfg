package com.uoc.tfg.model.auxiliar;

public class MatchPointsStats {

	private int idTeam;
	private String team;
	private int first;
	private int second;
	private int third;
	private int fourth;
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
	public int getFirst() {
		return first;
	}
	public void setFirst(int first) {
		this.first = first;
	}
	public int getSecond() {
		return second;
	}
	public void setSecond(int second) {
		this.second = second;
	}
	public int getThird() {
		return third;
	}
	public void setThird(int third) {
		this.third = third;
	}
	public int getFourth() {
		return fourth;
	}
	public void setFourth(int fourth) {
		this.fourth = fourth;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public MatchPointsStats(int idTeam, String team, int first, int second, int third, int fourth, int total) {
		this.idTeam = idTeam;
		this.team = team;
		this.first = first;
		this.second = second;
		this.third = third;
		this.fourth = fourth;
		this.total = total;
	}
	public MatchPointsStats() {
		
	}
	
}
