package com.uoc.tfg.bean;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.uoc.tfg.business.StatisticsFacadeBean;
import com.uoc.tfg.model.bd.Rating;
import com.uoc.tfg.model.bd.Statistics;

@ManagedBean(name="ratings")
@RequestScoped
public class ShowRating {
	private Rating ratings;
	private Statistics statistics;
	
	public ShowRating() throws Exception {
		
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		
		this.getRatings(Integer.parseInt(params.get("id")));
	}
	public Rating getRatings() {
		return ratings;
	}
	public void setRatings(Rating ratings) {
		this.ratings = ratings;
	}
	public Statistics getStatistics() {
		return statistics;
	}
	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}
	public void getRatings(int id) throws Exception {
		
		StatisticsFacadeBean sf = new StatisticsFacadeBean();
		
		this.ratings = sf.getRating(id);
		if(this.ratings.getLocation().equals("All"))
			this.ratings.setLocation("Tots");
		else 
			if(this.ratings.getLocation().equals("H"))
				this.ratings.setLocation("Casa");
			else
				this.ratings.setLocation("Fora");
		this.statistics = sf.getStatistics(this.ratings.getIdStatistics());
		
	}
}
