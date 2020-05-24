package com.uoc.tfg.business;

import java.sql.Date;
import java.util.List;

import com.uoc.tfg.model.auxiliar.Standing;
import com.uoc.tfg.model.bd.Rating;
import com.uoc.tfg.model.bd.Team;

public interface TeamFacade {

	public Team getTeam(int idTeam) throws Exception;
	public List<Standing> getStandings(String conference) throws Exception;
	public void calculateRanks(Date d) throws Exception;
	public void calculateRanks(Rating rat) throws Exception;
	public void calculateRanks(Date d, Rating rat) throws Exception;
	public String getPosition(Team t) throws Exception;
}
