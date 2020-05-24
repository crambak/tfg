package com.uoc.tfg.model.bd;

public class TeamRank {

	private int id;
	private int idTeam;
	private int idRank;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdTeam() {
		return idTeam;
	}
	public void setIdTeam(int idTeam) {
		this.idTeam = idTeam;
	}
	public int getIdRank() {
		return idRank;
	}
	public void setIdRank(int idRank) {
		this.idRank = idRank;
	}
	public TeamRank(int id, int idTeam, int idRank) {
		this.id = id;
		this.idTeam = idTeam;
		this.idRank = idRank;
	}
	public TeamRank() {
		
	}
}
