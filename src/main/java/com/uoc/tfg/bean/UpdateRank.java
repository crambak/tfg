package com.uoc.tfg.bean;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.uoc.tfg.business.StatisticsFacadeBean;
import com.uoc.tfg.model.bd.Rank;

@ManagedBean(name="updaterank")
@RequestScoped
public class UpdateRank {
	public Rank rank;
	public int id;
	public String name;
	public String description;
	public float min;
	public float max;
	public int idrating;
	
	public UpdateRank() throws Exception {
		
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		StatisticsFacadeBean sf = new StatisticsFacadeBean();
		
		if(params.get("id") != null) {
			this.getRank(sf, Integer.parseInt(params.get("id")));
		}
		if(params.get("idRating") != null) {
			this.idrating = Integer.parseInt(params.get("idRating"));
		}
	}
	
	public Rank getRank() {
		return rank;
	}
	public void setRank(Rank rank) {
		this.rank = rank;
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
	public int getIdrating() {
		return idrating;
	}
	public void setIdrating(int idrating) {
		this.idrating = idrating;
	}

	public void getRank(StatisticsFacadeBean sf, int id) throws Exception {
		
		this.rank = sf.getRank(id);
		this.id = this.rank.getId();
		this.name = this.rank.getName();
		this.description = this.rank.getDescription();
		this.min = this.rank.getMin();
		this.max = this.rank.getMax();
		this.idrating = this.rank.getIdRating();
		
	}
	
	public String update(int id, String name, String description, float min, float max, int idRating) throws Exception {
		
		StatisticsFacadeBean sf = new StatisticsFacadeBean();
		sf.updateRank(id, idRating, name, description, min, max);
		Rank r =sf.getRank(id);
		idRating = r.getIdRating();
		return "/ranks/showAllRanksView.xhtml?idRating="+idRating+"&faces-redirect=true";
	}
	
	public String showAllRank(int id) throws Exception {
		StatisticsFacadeBean sf = new StatisticsFacadeBean();
		Rank r =sf.getRank(id);
		int idRating = r.getIdRating();
		return "/ranks/showAllRanksView.xhtml?idRating="+idRating+"&faces-redirect=true";
	}
	
}
