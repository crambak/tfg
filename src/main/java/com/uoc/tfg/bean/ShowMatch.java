package com.uoc.tfg.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.uoc.tfg.business.MatchFacadeBean;
import com.uoc.tfg.model.auxiliar.MatchPointsStats;
import com.uoc.tfg.model.auxiliar.MatchReboundsStats;
import com.uoc.tfg.model.auxiliar.MatchTeam;
import com.uoc.tfg.model.bd.Match;

@ManagedBean(name="showmatch")
@RequestScoped
public class ShowMatch {
	private int idMatch;
	private String date;
	private String location;
	private MatchTeam mtHome;
	private MatchTeam mtAway;
	private Collection<MatchPointsStats> points;
	private Collection<MatchReboundsStats> rebounds;
	
	public int getIdMatch() {
		return idMatch;
	}
	public void setIdMatch(int idMatch) {
		this.idMatch = idMatch;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getLocation() {
		return location;
	}
	public MatchTeam getMtHome() {
		return mtHome;
	}
	public void setMtHome(MatchTeam mtHome) {
		this.mtHome = mtHome;
	}
	public MatchTeam getMtAway() {
		return mtAway;
	}
	public void setMtAway(MatchTeam mtAway) {
		this.mtAway = mtAway;
	}
	public Collection<MatchPointsStats> getPoints() {
		return points;
	}
	public void setPoints(Collection<MatchPointsStats> points) {
		this.points = points;
	}
	public Collection<MatchReboundsStats> getRebounds() {
		return rebounds;
	}
	public void setRebounds(Collection<MatchReboundsStats> rebounds) {
		this.rebounds = rebounds;
	}
	public ShowMatch() throws Exception {
		
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		this.idMatch = Integer.parseInt(params.get("idMatch"));

		MatchFacadeBean mf = new MatchFacadeBean();
		Match m = mf.getMatch(idMatch);
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		date = df.format(m.getDate());
		location = m.getLocation();
		mtHome = mf.getMatchTeam(m.getIdHomeTeam());
		mtAway = mf.getMatchTeam(m.getIdAwayTeam());
		points = mf.getMatchPoints(idMatch);
		rebounds = mf.getMatchRebounds(idMatch);
		
	}
	
	public Collection<MatchPointsStats> points() {
		return this.points;
	}
	
	public Collection<MatchReboundsStats> rebounds() {
		return this.rebounds;
	}
	
}
