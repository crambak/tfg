package com.uoc.tfg.model.auxiliar;

import java.util.List;

public class StatsRating {

	private String name;
	private String rank;
	private String oppRank;
	private float avg;
	private float min;
	private float max;
	private float last;
	private List<StatsRatingMatch> lstStats; 
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
	public String getOppRank() {
		return oppRank;
	}
	public void setOppRank(String oppRank) {
		this.oppRank = oppRank;
	}
	public float getAvg() {
		return avg;
	}
	public void setAvg(float avg) {
		this.avg = avg;
	}
	public float getMin() {
		return min;
	}
	public void setMin(float min) {
		this.min = min;
	}
	public float getMax() {
		return max;
	}
	public void setMax(float max) {
		this.max = max;
	}
	public float getLast() {
		return last;
	}
	public void setLast(float last) {
		this.last = last;
	}
	public List<StatsRatingMatch> getLstStats() {
		return lstStats;
	}
	public void setLstStats(List<StatsRatingMatch> lstStats) {
		this.lstStats = lstStats;
	}
	public StatsRating(String name, String rank, String oppRank, float avg,  float min, float max, float last, List<StatsRatingMatch> lstStats) {
		this.name = name;
		this.rank = rank;
		this.oppRank = oppRank;
		this.avg = avg;
		this.min = min;
		this.max = max;
		this.last = last;
		this.lstStats = lstStats;
	}
	public StatsRating() {
		
	}
	
}
