package com.uoc.tfg.model.rest;

import com.google.gson.annotations.SerializedName;

public class AwayTeam {

	@SerializedName("awayTeamStats")
	private TeamStats awayTeamStats;

	public AwayTeam() {
	}
	public TeamStats getAwayTeamStats() {
		return awayTeamStats;
	}
	public void setAwayTeamStats(TeamStats awayTeamStats) {
		this.awayTeamStats = awayTeamStats;
	}
	public AwayTeam(TeamStats awayTeamStats) {
		this.awayTeamStats = awayTeamStats;
	}
}
