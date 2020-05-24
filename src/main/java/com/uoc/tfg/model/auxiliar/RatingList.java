package com.uoc.tfg.model.auxiliar;

public class RatingList {

	private int id;
	private String statistics;
	private String name;
	private String description;
	private int days;
	private int numberMatchs;
	private String location;
	private boolean activeBol;
	private String active;
	private String calcul;
	private boolean automatic;
	private String buttonAct;
	private String buttonAut;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatistics() {
		return statistics;
	}
	public void setStatistics(String statistics) {
		this.statistics = statistics;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public int getNumberMatchs() {
		return numberMatchs;
	}
	public void setNumberMatchs(int numberMatchs) {
		this.numberMatchs = numberMatchs;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public boolean isActiveBol() {
		return activeBol;
	}
	public void setActiveBol(boolean activeBol) {
		this.activeBol = activeBol;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getCalcul() {
		return calcul;
	}
	public void setCalcul(String calcul) {
		this.calcul = calcul;
	}
	public boolean isAutomatic() {
		return automatic;
	}
	public void setAutomatic(boolean automatic) {
		this.automatic = automatic;
	}
	public String getButtonAct() {
		return buttonAct;
	}
	public void setButtonAct(String buttonAct) {
		this.buttonAct = buttonAct;
	}
	public String getButtonAut() {
		return buttonAut;
	}
	public void setButtonAut(String buttonAut) {
		this.buttonAut = buttonAut;
	}
	public RatingList(int id, String statistics, String name, String description, int days, int numberMatchs, String location, boolean activeBol, String active, String calcul, boolean automatic, String buttonAct, String buttonAut) {
		this.id = id;
		this.statistics = statistics;
		this.name = name;
		this.description = description;
		this.days = days;
		this.numberMatchs = numberMatchs;
		this.location = location;
		this.activeBol = activeBol;
		this.active = active;
		this.calcul = calcul;
		this.automatic = automatic;
		this.buttonAct = buttonAct;
		this.buttonAut = buttonAut;
	}
	public RatingList() {
	
	}

}
