package com.uoc.tfg.model.rest;

import com.google.gson.annotations.SerializedName;

public class HomeTeam {

	@SerializedName("homeTeamStats")
	private TeamStats homeTeamStats;

	public HomeTeam() {
	}
	public TeamStats getHomeTeamStats() {
		return homeTeamStats;
	}
	public void setHomeTeamStats(TeamStats homeTeamStats) {
		this.homeTeamStats = homeTeamStats;
	}
	public HomeTeam(TeamStats homeTeamStats) {
		this.homeTeamStats = homeTeamStats;
	}
}
