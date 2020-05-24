package com.uoc.tfg.model.auxiliar;

public class StatisticsList {

	private int id;
	private String type;
	private String description;
	private boolean activeBol;
	private String active;
	private String buttonAct;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getButtonAct() {
		return buttonAct;
	}
	public void setButtonAct(String buttonAct) {
		this.buttonAct = buttonAct;
	}
	public StatisticsList(int id, String type, String description, boolean activeBol, String active, String buttonAct) {
		this.id = id;
		this.type = type;
		this.description = description;
		this.activeBol = activeBol;
		this.active = active;
		this.buttonAct = buttonAct;
	}
	public StatisticsList() {
		
	}
}
