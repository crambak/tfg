package com.uoc.tfg.bean;

import java.util.Collection;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.uoc.tfg.business.MatchFacadeBean;
import com.uoc.tfg.business.TeamFacadeBean;
import com.uoc.tfg.model.auxiliar.GameTeam;
import com.uoc.tfg.model.bd.Team;

@ManagedBean(name="showteam")
@RequestScoped
public class ShowTeam {
	private int idTeam;
	private String name;
	private String balance;
	private String position;
	private Collection<GameTeam> lstPastGames;
	private Collection<GameTeam> lstNextGames;
	
	public int getIdTeam() {
		return idTeam;
	}
	public void setIdTeam(int idTeam) {
		this.idTeam = idTeam;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Collection<GameTeam> getLstPastGames() {
		return lstPastGames;
	}
	public void setLstPastGames(Collection<GameTeam> lstPastGames) {
		this.lstPastGames = lstPastGames;
	}
	public Collection<GameTeam> getLstNextGames() {
		return lstNextGames;
	}
	public void setLstNextGames(Collection<GameTeam> lstNextGames) {
		this.lstNextGames = lstNextGames;
	}

	public ShowTeam() throws Exception {
		
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		this.idTeam = Integer.parseInt(params.get("idTeam"));
		
		TeamFacadeBean tf = new TeamFacadeBean();
		MatchFacadeBean mf = new MatchFacadeBean();
		Team t = tf.getTeam(this.idTeam);
		this.position = tf.getPosition(t);
		this.name = t.getCity()+" "+t.getName()+" "+t.getAbbreviation();
		this.balance = t.getWins()+"/"+t.getLoses();
		lstPastGames = mf.getLastGames(idTeam, 5);
		lstNextGames = mf.getNextGames(idTeam, 5);

	}
	
	public Collection<GameTeam> gamesLastGames() {
		return this.lstPastGames;
	}
	
	public Collection<GameTeam> gamesNextGames() {
		return this.lstNextGames;
	}
	
	public void showGame(int idMatch) {
		
	}
}
