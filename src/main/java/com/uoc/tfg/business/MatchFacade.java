package com.uoc.tfg.business;

import java.sql.Date;
import java.util.List;

import com.uoc.tfg.model.auxiliar.Game;
import com.uoc.tfg.model.auxiliar.GameTeam;
import com.uoc.tfg.model.auxiliar.MatchPointsStats;
import com.uoc.tfg.model.auxiliar.MatchReboundsStats;
import com.uoc.tfg.model.auxiliar.MatchTeam;
import com.uoc.tfg.model.auxiliar.Stats;
import com.uoc.tfg.model.auxiliar.StatsRating;
import com.uoc.tfg.model.auxiliar.StatsRatingMatch;
import com.uoc.tfg.model.bd.Match;

public interface MatchFacade {

	public Match getMatch(int idMatch) throws Exception;
	public void getFullGameSchedule() throws Exception;
	public void getDayGamelogs(Date date) throws Exception;
	public void getPastGamelogs(Date dateIni, Date dateFin) throws Exception;
	public void importMatch(int idMatch) throws Exception;
	public List<Game> getGames(Date day) throws Exception;
	public List<GameTeam> getLastGames(int idTeam, int num) throws Exception;
	public List<GameTeam> getNextGames(int idTeam, int num) throws Exception;
	public MatchTeam getMatchTeam(int idTeam) throws Exception;
	public List<MatchPointsStats> getMatchPoints(int idMatch) throws Exception;
	public List<MatchReboundsStats> getMatchRebounds(int idMatch) throws Exception;
	public List<Stats> getStats(int idMatch, int idStats) throws Exception; 
	public Date getLastDateMatch() throws Exception;
	public List<StatsRating> getStatsRating(int idMatch, int idRating, float stakeHome, float stakeAway, float stakeTotal) throws Exception;
	public List<StatsRatingMatch> calculateStakes(List<StatsRatingMatch> sr, float stakeHome, float stakeAway, float stakeTotal) throws Exception;

}
