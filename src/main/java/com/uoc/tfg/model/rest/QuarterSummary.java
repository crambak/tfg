package com.uoc.tfg.model.rest;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class QuarterSummary {

	@SerializedName("quartersummary")
	private List<Quarter> quarter;
	@SerializedName("quarterTotals")
	private Quarter quarterTotals;

	public QuarterSummary() {
	}
	public List<Quarter> getQuarter() {
		return quarter;
	}
	public void setQuarter(List<Quarter> quarter) {
		this.quarter = quarter;
	}
	public Quarter getQuarterTotals() {
		return quarterTotals;
	}
	public void setQuarterTotals(Quarter quarterTotals) {
		this.quarterTotals = quarterTotals;
	}
	public QuarterSummary(List<Quarter> quarter, Quarter quarterTotals) {
		this.quarter = quarter;
		this.quarterTotals = quarterTotals;
	}
}
