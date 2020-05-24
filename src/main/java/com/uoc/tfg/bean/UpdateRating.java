package com.uoc.tfg.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.uoc.tfg.business.StatisticsFacadeBean;
import com.uoc.tfg.model.auxiliar.StatisticsList;
import com.uoc.tfg.model.bd.Rating;

@ManagedBean(name="updaterating")
@RequestScoped
public class UpdateRating {
	public Rating rating;
	public List<SelectItem> listStatistics;
	public int id;
	public String name;
	public String description;
	public int days;
	public int numberMatchs;
	public String location;
	public int statistic;
	
	public UpdateRating() throws Exception {
		
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		StatisticsFacadeBean sf = new StatisticsFacadeBean();
		StatisticsList st = new StatisticsList();
		if(params.get("id") != null) {
			this.getRating(sf, Integer.parseInt(params.get("id")));
		}
		List<StatisticsList> statistics = sf.getAllStatistics();
		listStatistics = new ArrayList<SelectItem>();
		for(int i = 0; i < statistics.size(); i++) {
			st = statistics.get(i);
			listStatistics.add(new SelectItem(st.getId(), st.getDescription()));
		}
	}
	public Rating getRating() {
		return rating;
	}
	public void setRating(Rating rating) {
		this.rating = rating;
	}
	public List<SelectItem> getListStatistics() {
		return listStatistics;
	}
	public void setListStatistics(List<SelectItem> listStatistics) {
		this.listStatistics = listStatistics;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getStatistic() {
		return statistic;
	}
	public void setStatistic(int statistic) {
		this.statistic = statistic;
	}
	public void getRating(StatisticsFacadeBean sf, int id) throws Exception {
		
		this.rating = sf.getRating(id);
		this.id = this.rating.getId();
		this.name = this.rating.getName();
		this.description = this.rating.getDescription();
		this.days = this.rating.getDays();
		this.numberMatchs = this.rating.getNumberMatchs();
		this.location = this.rating.getLocation();
		this.statistic = this.rating.getIdStatistics();
	}
	
	public String update(int id, String name, String description, int days, int numberMatchs, String location, int idStatistics) throws Exception {
		
		StatisticsFacadeBean sf = new StatisticsFacadeBean();
		sf.updateRating(id, idStatistics, name, description, days, numberMatchs, location);
		
		return "/ratings/showAllRatingsView.xhtml?faces-redirect=true";
	}
	
}
