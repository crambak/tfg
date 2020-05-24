package com.uoc.tfg.bean;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.uoc.tfg.business.StatisticsFacadeBean;
import com.uoc.tfg.model.auxiliar.StatisticsList;

@ManagedBean(name="statisticslist")
@RequestScoped
public class ListAllStatistics {
	private Collection<StatisticsList> lstStatistics;

	public Collection<StatisticsList> getLstStatistics() {
		return lstStatistics;
	}
	public void setLstStatistics(Collection<StatisticsList> lstStatistics) {
		this.lstStatistics = lstStatistics;
	}

	public Collection<StatisticsList> statisticsList() throws Exception {
		
		StatisticsFacadeBean sf = new StatisticsFacadeBean();
		
		lstStatistics = sf.getAllStatistics();
		
		return lstStatistics;
	}
	
	public String showStatistics(int id) {
		return "/statistics/showStatisticsView.xhtml?faces-redirect=true&id="+id;
	}
	
	public String updateStatistics(int id) {
		return "/statistics/updateStatisticsView.xhtml?faces-redirect=true&id="+id;
	}
	
	public String activeStatistics(int id, boolean active) throws Exception {
		
		StatisticsFacadeBean sf = new StatisticsFacadeBean();
		
		sf.activeStatistics(id, !active);
		
		return "/statistics/showAllStatisticsView.xhtml?faces-redirect=true";
	}
}
