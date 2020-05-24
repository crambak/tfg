package com.uoc.tfg.model.rest;

import com.google.gson.annotations.SerializedName;

public class Rebounds {
	@SerializedName("text")
	private int text;
	
	public Rebounds() {
		
	}
	public int getText() {
		return text;
	}
	public void setText(int text) {
		this.text = text;
	}
	public Rebounds(int text) {
		this.text = text;
	}
	
}
