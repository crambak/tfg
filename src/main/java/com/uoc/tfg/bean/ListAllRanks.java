package com.uoc.tfg.bean;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.uoc.tfg.business.StatisticsFacadeBean;
import com.uoc.tfg.business.TeamFacadeBean;
import com.uoc.tfg.model.auxiliar.RankTeam;
import com.uoc.tfg.model.bd.Rank;
import com.uoc.tfg.model.bd.Rating;

@ManagedBean(name="ranklist")
@SessionScoped
public class ListAllRanks {
	private Collection<Rank> lstRanks;
	private int idRating;
	private String rating; 
	private List<RankTeam> lstTeamRank;
	
	public ListAllRanks() throws Exception {
		
		StatisticsFacadeBean sf = new StatisticsFacadeBean();
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		if(params.get("idRating") != null) {
			this.idRating = Integer.parseInt(params.get("idRating"));
			Rating rat = sf.getRating(idRating);
			this.rating = rat.getName() + " - " + rat.getDescription();
		}

	}

	public Collection<Rank> getLstRanks() {
		return lstRanks;
	}
	public void setLstRanks(Collection<Rank> lstRanks) {
		this.lstRanks = lstRanks;
	}
	public int getIdRating() {
		return idRating;
	}
	public void setIdRating(int idRating) {
		this.idRating = idRating;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}

	public Collection<Rank> ranksList() throws Exception {
		
		StatisticsFacadeBean sf = new StatisticsFacadeBean();
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		if(params.get("idRating") != null) {
			this.idRating = Integer.parseInt(params.get("idRating"));
			
			lstRanks = sf.getAllRanks(idRating);
			lstTeamRank = sf.getAllRankTeams(idRating);
		}
		return lstRanks;
	}
	
	public String addRank( int idRating) {
		return "/ranks/addRankView.xhtml?idRating="+idRating+"&faces-redirect=true";
	}
	
	public String showRank(int id, int idRating) {
		return "/ranks/showRankView.xhtml?id="+id+"&idRating="+idRating+"&faces-redirect=true";
	}
	
	public String updateRank(int id, int idRating) {
		return "/ranks/updateRankView.xhtml?id="+id+"&idRating="+idRating+"&faces-redirect=true";
	}
	
	public String calculateRank(int idRating) throws Exception {
		
		TeamFacadeBean t = new TeamFacadeBean();
		StatisticsFacadeBean s = new StatisticsFacadeBean();
		Rating r = s.getRating(idRating);
		t.calculateRanks(r);
		
		return "/ranks/showAllRanksView.xhtml?idRating="+idRating+"&faces-redirect=true";
	}
	
	public String deleteRank(String id, String idRating) throws Exception {
		
		StatisticsFacadeBean sf = new StatisticsFacadeBean();
		
		sf.deleteRank(Integer.parseInt(id));
		
		return "/ranks/showAllRanksView.xhtml?idRating="+idRating+"&faces-redirect=true";
	}
	
	public List<RankTeam> getListRankTeam() {
		return lstTeamRank;
	}
	
}
