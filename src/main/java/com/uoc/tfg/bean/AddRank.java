package com.uoc.tfg.bean;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.uoc.tfg.business.StatisticsFacadeBean;

@ManagedBean(name="addrank")
@RequestScoped
public class AddRank {
	public String id;
	public String name;
	public String description;
	public int min;
	public int max;
	public int idRating;
	
	public AddRank() throws Exception {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		
		if(params.get("idRating") != null) {
			this.idRating = Integer.parseInt(params.get("idRating"));
		}
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getIdRating() {
		return idRating;
	}
	public void setIdRating(int idRating) {
		this.idRating = idRating;
	}

	public String addRank(String name, String description, float min, float max, int idRating) throws Exception {
		
		StatisticsFacadeBean sf = new StatisticsFacadeBean();
		
		sf.addRank(idRating, name, description, min, max);
		
		return "/ranks/showAllRanksView.xhtml?idRating="+idRating+"&faces-redirect=true";
	}
	
}
