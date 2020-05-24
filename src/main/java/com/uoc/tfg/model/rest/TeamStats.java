package com.uoc.tfg.model.rest;

import com.google.gson.annotations.SerializedName;

public class TeamStats {

	@SerializedName("offreb")
	private Rebounds offreb;
	@SerializedName("defreb")
	private Rebounds defreb;
	@SerializedName("reb")
	private Rebounds reb;
	public TeamStats() {
	}
	public Rebounds getOffreb() {
		return offreb;
	}
	public void setOffreb(Rebounds offreb) {
		this.offreb = offreb;
	}
	public Rebounds getDefreb() {
		return defreb;
	}
	public void setDefreb(Rebounds defreb) {
		this.defreb = defreb;
	}
	public Rebounds getReb() {
		return reb;
	}
	public void setReb(Rebounds reb) {
		this.reb = reb;
	}
	public TeamStats(Rebounds offreb, Rebounds defreb, Rebounds reb) {
		this.offreb = offreb;
		this.defreb = defreb;
		this.reb = reb;
	}
}
