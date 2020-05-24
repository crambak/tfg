package com.uoc.tfg.model.bd;

public class Rank {

	private int id;
	private int idRating;
	private String name;
	private String description;
	private float min;
	private float max;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdRating() {
		return idRating;
	}
	public void setIdRating(int idRating) {
		this.idRating = idRating;
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
	public Rank(int id, int idRating, String name, String description, float min, float max) {
		this.id = id;
		this.idRating = idRating;
		this.name = name;
		this.description = description;
		this.min = min;
		this.max = max;
	}
	public Rank() {
		
	}

}
