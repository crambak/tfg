package com.uoc.tfg.bean;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.uoc.tfg.business.StatisticsFacadeBean;
import com.uoc.tfg.model.auxiliar.RatingList;

@ManagedBean(name="ratinglist")
@RequestScoped
public class ListAllRatings {
	private Collection<RatingList> lstRatings;

	public Collection<RatingList> getLstRatings() {
		return lstRatings;
	}

	public void setLstRatings(Collection<RatingList> lstRatings) {
		this.lstRatings = lstRatings;
	}
	public Collection<RatingList> ratingsList() throws Exception {
		
		StatisticsFacadeBean sf = new StatisticsFacadeBean();
		
		lstRatings = sf.getAllRatings();
		
		return lstRatings;
	}
	
	public String showRatings(int id) {
		return "/ratings/showRatingsView.xhtml?faces-redirect=true&id="+id;
	}
	
	public String updateRatings(int id) {
		return "/ratings/updateRatingsView.xhtml?faces-redirect=true&id="+id;
	}
	
	public String activeRatings(int id, boolean active) throws Exception {
		
		StatisticsFacadeBean sf = new StatisticsFacadeBean();
		
		sf.activeRating(id, !active);
		
		
		return "/ratings/showAllRatingsView.xhtml?faces-redirect=true";
	}
	
	public String autoRatings(int id, boolean automatic) throws Exception {
		StatisticsFacadeBean sf = new StatisticsFacadeBean();
		
		sf.autoRating(id, automatic);
		
		return "/ratings/showAllRatingsView.xhtml?faces-redirect=true";
	}
	
	
	public String showRanks(int id) {
		return "/ranks/showAllRanksView.xhtml?faces-redirect=true&idRating="+id;
	}
}
