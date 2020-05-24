package com.uoc.tfg.model.auxiliar;

public class Stats {

	private String name;
	private float all;
	private float last10;
	private float last5;
	private float home;
	private float last15;
	private float last30;
	private float last;
	private float last7;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getAll() {
		return all;
	}
	public void setAll(float all) {
		this.all = all;
	}
	public float getLast10() {
		return last10;
	}
	public void setLast10(float last10) {
		this.last10 = last10;
	}
	public float getLast5() {
		return last5;
	}
	public void setLast5(float last5) {
		this.last5 = last5;
	}
	public float getHome() {
		return home;
	}
	public void setHome(float home) {
		this.home = home;
	}
	public float getLast15() {
		return last15;
	}
	public void setLast15(float last15) {
		this.last15 = last15;
	}
	public float getLast30() {
		return last30;
	}
	public void setLast30(float last30) {
		this.last30 = last30;
	}
	public float getLast() {
		return last;
	}
	public void setLast(float last) {
		this.last = last;
	}
	public float getLast7() {
		return last7;
	}
	public void setLast7(float last7) {
		this.last7 = last7;
	}
	public Stats(String name, float all, float last10,  float last5, float home, float last15, float last30, float last, float last7) {
		this.name = name;
		this.all = all;
		this.last10 = last10;
		this.last5 = last5;
		this.home = home;
		this.last15 = last15;
		this.last30 = last30;
		this.last = last;
		this.last7 = last7;
	}
	public Stats() {
		
	}
	
}
