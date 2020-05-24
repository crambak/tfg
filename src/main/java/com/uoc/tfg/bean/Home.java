package com.uoc.tfg.bean;

import java.sql.Date;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.uoc.tfg.business.MatchFacadeBean;
import com.uoc.tfg.business.TeamFacadeBean;
import com.uoc.tfg.model.auxiliar.Game;
import com.uoc.tfg.model.auxiliar.Standing;

@ManagedBean(name="home")
@RequestScoped
public class Home {
	private Collection<Game> lstGamesToday;
	private Collection<Game> lstGamesYesterday;
	private List<Standing> lstEast;
	private List<Standing> lstWest;
	
	public Collection<Game> getLstGamesToday() {
		return lstGamesToday;
	}
	public void setLstGamesToday(Collection<Game> lstGamesToday) {
		this.lstGamesToday = lstGamesToday;
	}
	public Collection<Game> getLstGamesYesterday() {
		return lstGamesYesterday;
	}
	public void setLstGamesYesterday(Collection<Game> lstGamesYesterday) {
		this.lstGamesYesterday = lstGamesYesterday;
	}
	public List<Standing> getLstEast() {
		return lstEast;
	}
	public void setLstEast(List<Standing> lstEast) {
		this.lstEast = lstEast;
	}
	public List<Standing> getLstWest() {
		return lstWest;
	}
	public void setLstWast(List<Standing> lstWest) {
		this.lstWest = lstWest;
	}
	
	public Home() throws Exception {
		
		TeamFacadeBean tf = new TeamFacadeBean();
		MatchFacadeBean mf = new MatchFacadeBean();
		
		Date yesterday = mf.getLastDateMatch();
		if(yesterday != null) {
			Calendar calendar = Calendar.getInstance();
	        calendar.setTime(yesterday); 
	        calendar.add(Calendar.DAY_OF_YEAR, 1);
			Date today = new Date(calendar.getTimeInMillis());
			lstEast = tf.getStandings("E");
			lstWest = tf.getStandings("W");
			lstGamesToday = mf.getGames(today);
			lstGamesYesterday = mf.getGames(yesterday);
		}

	}
	
	public Collection<Game> gamesToday() {
		return this.lstGamesToday;
	}
	
	public Collection<Game> gamesYesterday() {
		return this.lstGamesYesterday;
	}
	
	public List<Standing> eastStanding() {
		return lstEast;
	}
	
	public List<Standing> westStanding() {
		return lstWest;
	}
	
	public void showGame(int idMatch) {
		
	}
	
	public String showTeam(int idTeam) {
		return "/matchs/showTeamView.xhtml?idTeam="+idTeam+"&faces-redirect=true";
	}
}
