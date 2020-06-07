package com.uoc.tfg.bean;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.uoc.tfg.business.StatisticsFacadeBean;
import com.uoc.tfg.model.bd.Statistics;

@ManagedBean(name="updatestatistics")
@RequestScoped
public class UpdateStatistic {
	public Statistics statistics;
	public String id;
	public String type;
	public String description;
	
	public UpdateStatistic() throws Exception {
		
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		
		if(params.get("id") != null)
			this.getStatistics(Integer.parseInt(params.get("id")));
	}
	public Statistics getStatistics() {
		return statistics;
	}
	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public void getStatistics(int id) throws Exception {
		
		StatisticsFacadeBean sf = new StatisticsFacadeBean();
		
		this.statistics = sf.getStatistics(id);
		this.id = this.statistics.getId()+"";
		this.type = this.statistics.getType();
		this.description = this.statistics.getDescription();
	}
	public String update(String id, String description) throws Exception {
		
		StatisticsFacadeBean sf = new StatisticsFacadeBean();
		sf.updateStatistics(Integer.parseInt(id), description);
		
		return "/statistics/showAllStatisticsView.xhtml?faces-redirect=true";
	}
}
