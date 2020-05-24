package com.uoc.tfg.business;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.uoc.tfg.dao.MatchDAO;
import com.uoc.tfg.dao.StatisticsDAO;
import com.uoc.tfg.dao.TeamDAO;
import com.uoc.tfg.model.auxiliar.Standing;
import com.uoc.tfg.model.bd.Rank;
import com.uoc.tfg.model.bd.Rating;
import com.uoc.tfg.model.bd.Team;

public class TeamFacadeBean implements TeamFacade {

	/**
	 * Obtenir la informació bàsica d'un equip
	 * @throws Exception 
	 */
	public Team getTeam(int idTeam) throws Exception {
		TeamDAO teamDAO = new TeamDAO();
				
		Team team = teamDAO.getTeam(idTeam);
		
		teamDAO.close();
		return team;
	}

	/**
	 * Retornem la classificació dels equips segons les seves victòries i derrotes
	 */
	public List<Standing> getStandings(String conference) throws Exception {
		
		TeamDAO tDAO = new TeamDAO();
		List<Standing> lstStanding = tDAO.getStanding(conference);
		tDAO.close();
		
		return lstStanding;
	}
	
	/**
	 * Classifiquem els equips en els diferents rangs del rànquing escollit
	 */
	public void calculateRanks(Date d) throws Exception {
		
		StatisticsDAO sDAO = new StatisticsDAO();
		List<Rating> lstRat = sDAO.getAllRatings();
		Rating rat = new Rating();
		for(int i = 0; i < lstRat.size(); i++) {
			rat = lstRat.get(i);
			if(rat.isActive())
				calculateRanks(d, rat);
		}
		
		sDAO.close();
		
	}
	
	/**
	 * Classifiquem els equips en els diferents rangs del rànquing escollit
	 */
	public void calculateRanks(Rating rat) throws Exception {
		
		MatchDAO mDAO = new MatchDAO();
		Date d = mDAO.getMaxDate();
		if(d!=null)
			calculateRanks(d, rat);
		
		mDAO.close();
	}
	
	/**
	 * Classifiquem els equips en els diferents rangs del rànquing escollit
	 */
	public void calculateRanks(Date d, Rating rat) throws Exception {
		
		StatisticsDAO sDAO = new StatisticsDAO();
		TeamDAO tDAO = new TeamDAO();
		MatchDAO mDAO = new MatchDAO();
		tDAO.cleanTeamsRanks(rat.getId());
		Team t = new Team();
		Rank r = new Rank();
		float avg = 0;
		Calendar cal = GregorianCalendar.getInstance();
	    cal.setTime(d);
	    cal.add(GregorianCalendar.DATE, -1 * rat.getDays());
	    d = new Date(cal.getTimeInMillis());
		List<Rank> lstRank = sDAO.getRanksRating(rat.getId());
		List<Team> lstTeam = tDAO.getAllTeams();
		for(int i = 0; i < lstTeam.size(); i++) {
			t = lstTeam.get(i);
			avg = mDAO.getTeamMatchStatistics(t.getId(), rat.getIdStatistics(), rat.getNumberMatchs(), d, rat.getLocation(), false);
			for(int j = 0; j < lstRank.size(); j++) {
				r = lstRank.get(j);
				if(avg < r.getMax() && avg >= r.getMin()) {
					tDAO.addTeamRank(t.getId(), r.getId());
				}
			}
		}
		
		tDAO.close();
		sDAO.close();
		mDAO.close();
	}

	public String getPosition(Team t) throws Exception {
		TeamFacadeBean tf = new TeamFacadeBean();
		List<Standing> lstStanding = tf.getStandings(t.getConference());
		Standing st = new Standing();
		for(int i = 0; i < lstStanding.size(); i++) {
			st = lstStanding.get(i);
			if(st.getIdTeam() == t.getId()) { 
				return st.getPosition()+" "+st.getConference();
			}
		}
		return null;
	}
}
