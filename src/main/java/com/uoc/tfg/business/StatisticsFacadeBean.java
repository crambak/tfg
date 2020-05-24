package com.uoc.tfg.business;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;

import com.uoc.tfg.dao.MatchDAO;
import com.uoc.tfg.dao.StatisticsDAO;
import com.uoc.tfg.dao.TeamDAO;
import com.uoc.tfg.model.auxiliar.RankTeam;
import com.uoc.tfg.model.auxiliar.RatingList;
import com.uoc.tfg.model.auxiliar.StatisticsList;
import com.uoc.tfg.model.bd.Rank;
import com.uoc.tfg.model.bd.Rating;
import com.uoc.tfg.model.bd.Statistics;
import com.uoc.tfg.model.bd.Team;

public class StatisticsFacadeBean implements StatisticsFacade {

	/**
	 * Afegir un tipus d'estadística
	 */
	public void addStatistics(String type, String description) throws Exception {

		StatisticsDAO DAO = new StatisticsDAO();
		DAO.addStatistics(type, description);
		DAO.close();
		
	}

	/**
	 * Obtenim la informació bàsica d'una estadística
	 */
	public Statistics getStatistics(int idStatistics) throws Exception {
		
		StatisticsDAO DAO = new StatisticsDAO();
		Statistics st = DAO.getStatistics(idStatistics);
		DAO.close();
		return st;
		
	}

	/**
	 * Actualitzem la informació d'una estadística
	 */
	public void updateStatistics(int idStatistics, String description) throws Exception {
		StatisticsDAO DAO = new StatisticsDAO();
		DAO.updateStatistics(idStatistics, description);
		DAO.close();
		
	}

	/**
	 * Activem o desactivem una estadística concreta
	 */
	public void activeStatistics(int idStatistics, boolean active) throws Exception {
		StatisticsDAO DAO = new StatisticsDAO();
		DAO.activeStatistics(idStatistics, active);
		DAO.close();
		
	}

	/**
	 * Afegim un nou rànquing
	 */
	public void addRating(int idStatistics, String name, String description, int days, int numberMatchs, String location) throws Exception {
		StatisticsDAO DAO = new StatisticsDAO();
		DAO.addRating(idStatistics, name, description, days, numberMatchs, location);
		List<Rating> lstRat = DAO.getAllRatings();
		int idRating = lstRat.get(lstRat.size()-1).getId();
		createRanks(idRating);
		DAO.close();
		
	}

	/**
	 * Obtenim la informació d'un rànquing
	 */
	public Rating getRating(int idRating) throws Exception {
		Rating rat = new Rating();
		StatisticsDAO DAO = new StatisticsDAO();
		rat = DAO.getRating(idRating);
		DAO.close();
		
		return rat;
	}

	/**
	 * Actualitzem la informació d'un rànquing
	 */
	public void updateRating(int idRating, int idStatistics, String name, String description, int days, int numberMatchs, String location) throws Exception {
		StatisticsDAO DAO = new StatisticsDAO();
		DAO.updateRating(idRating, idStatistics, name, description, days, numberMatchs, location);
		DAO.close();
		
	}

	/**
	 * Activem o desactivem un rànquing 
	 */
	public void activeRating(int idRating, boolean active) throws Exception {
		StatisticsDAO sDAO = new StatisticsDAO();
		TeamDAO tDAO = new TeamDAO();
		List<Rank> lstR = new ArrayList<Rank>();
		sDAO.activeRating(idRating, active);
		TeamFacadeBean tf = new TeamFacadeBean();
		MatchDAO mDAO = new MatchDAO();
		Date d = mDAO.getMaxDate();
		if(d!=null) {
			Rating r = sDAO.getRating(idRating);
			if(active) {
				if(r.isAutomatic()) {
					lstR = sDAO.getRanksRating(r.getId());
					for(int i = 0; i < lstR.size(); i++) {
						sDAO.deleteRank(lstR.get(i).getId());
					}
					createRanks(r.getId());
				}
				tf.calculateRanks(d, r);
			}
		}
		sDAO.close();
		mDAO.close();
		tDAO.close();
	}
	
	/**
	 * Automatitzem o posem manual un rànquing
	 */
	public void autoRating(int idRating, boolean automatic) throws Exception {
		StatisticsDAO sDAO = new StatisticsDAO();
		TeamDAO tDAO = new TeamDAO();
		List<Rank> lstR = new ArrayList<Rank>();
		sDAO.autoRating(idRating, !automatic);
		TeamFacadeBean tf = new TeamFacadeBean();
		MatchDAO mDAO = new MatchDAO();
		Date d = mDAO.getMaxDate();
		if(d!=null) {
			Rating r = sDAO.getRating(idRating);
			if(r.isActive()) {
				if(r.isAutomatic()) {
					lstR = sDAO.getRanksRating(r.getId());
					for(int i = 0; i < lstR.size(); i++) {
						sDAO.deleteRank(lstR.get(i).getId());
					}
					createRanks(r.getId());
				}
				tf.calculateRanks(d, r);
			}
		}
		sDAO.close();
		mDAO.close();
		tDAO.close();
	}

	/**
	 * Afegim un rang
	 */
	public void addRank(int idRating, String name, String description, float min, float max) throws Exception {
		StatisticsDAO DAO = new StatisticsDAO();
		DAO.addRank(idRating, name, description, min, max);
		DAO.close();
		
	}

	/**
	 * Obtenim la informació d'un rang
	 * @throws Exception 
	 */
	public Rank getRank(int idRank) throws Exception {
		StatisticsDAO DAO = new StatisticsDAO();
		Rank r = DAO.getRank(idRank);
		DAO.close();
		return r;
	}

	/**
	 * Actualitzem la informació d'un rang
	 */
	public void updateRank(int idRank, int idRating, String name, String description, float min, float max) throws Exception {
		StatisticsDAO DAO = new StatisticsDAO();
		DAO.updateRank(idRank, idRating, name, description, min, max);
		DAO.close();
	}

	/**
	 * Eliminem un rang
	 */
	public void deleteRank(int idRank) throws Exception {
		StatisticsDAO DAO = new StatisticsDAO();
		DAO.deleteRank(idRank);
		DAO.close();
		
	}

	/**
	 * Obtenim un llistat amb totes les estadístiques
	 */
	public List<RatingList> getAllRatings() throws Exception {
		StatisticsDAO DAO = new StatisticsDAO();
		List<Rating> lstR = DAO.getAllRatings();
		List<RatingList> lstRatings = new ArrayList<RatingList>();
		Rating r = new Rating();
		RatingList rl = new RatingList();
		Statistics s = new Statistics();
		for(int i = 0; i < lstR.size(); i++) {
			rl = new RatingList();
			r = lstR.get(i);
			rl.setId(r.getId());
			rl.setName(r.getName());
			rl.setDescription(r.getDescription());
			rl.setDays(r.getDays());
			rl.setNumberMatchs(r.getNumberMatchs());
			rl.setLocation(r.getLocation());
			rl.setActiveBol(r.isActive());
			if(r.isActive()) {
				rl.setActive("Activada");
				rl.setButtonAct("Desactivar");
			} else {
				rl.setActive("Desactivada");
				rl.setButtonAct("Activar");
			}
			rl.setAutomatic(r.isAutomatic());
			if(r.isAutomatic()) {
				rl.setCalcul("Automàtic");
				rl.setButtonAut("Manual");
			} else {
				rl.setCalcul("Manual");

				rl.setButtonAut("Automàtic");
			}
			s = DAO.getStatistics(r.getIdStatistics());
			rl.setStatistics(s.getDescription());
			lstRatings.add(rl);
		}
		
		
		DAO.close();
		return lstRatings;
	}

	/**
	 * Obtenim un llistat amb tots els rànquings
	 **/
	public List<StatisticsList> getAllStatistics() throws Exception {
		StatisticsDAO DAO = new StatisticsDAO();
		List<StatisticsList> lstSL = new ArrayList<StatisticsList>();
		StatisticsList sl = new StatisticsList();
		List<Statistics> lstStatistics = DAO.getAllStatistics();
		Statistics st = new Statistics();
		for(int i = 0; i < lstStatistics.size(); i++) {
			sl = new StatisticsList();
			st = lstStatistics.get(i);
			sl.setId(st.getId());
			sl.setType(st.getType());
			sl.setDescription(st.getDescription());
			sl.setActiveBol(st.isActive());
			if(sl.isActiveBol()) {
				sl.setActive("Activada");
				sl.setButtonAct("Desactivar");
			} else {
				sl.setActive("Desactivada");
				sl.setButtonAct("Activar");
			}
			lstSL.add(sl);
		}
		DAO.close();
		return lstSL;
	}

	/**
	 * Obtenim la inforamció de tots els rangs d'un rànquing
	 */
	public List<Rank> getAllRanks(int idRating) throws Exception {
		StatisticsDAO DAO = new StatisticsDAO();
		List<Rank> lstRank = DAO.getRanksRating(idRating);
		DAO.close();
		return lstRank;
	}
	
	/**
	 * Obtenim les dades d'una estadistica concreta 
	 */
	public List<Rating> getRatingsStats(int idStatistics) throws Exception {
		StatisticsDAO DAO = new StatisticsDAO();
		List<Rating> lstRank = DAO.getRatingStatistics(idStatistics);
		DAO.close();
		return lstRank;
	}

	/**
	 * Obtenim les dades de tots els equips d'un rànquing concret
	 */
	public List<RankTeam> getAllRankTeams(int idRating) throws Exception {
		List<RankTeam> lstRT = new ArrayList<>();
		RankTeam rt = new RankTeam();
		StatisticsDAO sDAO = new StatisticsDAO();
		TeamDAO tDAO = new TeamDAO();
		MatchDAO mDAO = new MatchDAO();
		Team t = new Team();
		Rank r = new Rank();
		float avg = 0;
	    Rating rat = sDAO.getRating(idRating);
	    Date d = mDAO.getMaxDate();
	    if(d!= null) {
		    Calendar cal = GregorianCalendar.getInstance();
		    cal.setTime(d);
		    cal.add(GregorianCalendar.DATE, -1 * rat.getDays());
		    d = new Date(cal.getTimeInMillis());
			List<Rank> lstRank = sDAO.getRanksRating(rat.getId());
			List<Team> lstTeam = tDAO.getAllTeams();
			for(int i = 0; i < lstTeam.size(); i++) {
				t = lstTeam.get(i);
				rt = new RankTeam();
				rt.setIdTeam(t.getId());
				rt.setName(t.getCity()+" "+t.getName());
				avg = mDAO.getTeamMatchStatistics(t.getId(), rat.getIdStatistics(), rat.getNumberMatchs(), d, rat.getLocation(), false);
				rt.setAvg(round2(avg));
				rt.setRank("No rank");
				for(int j = 0; j < lstRank.size(); j++) {
					r = lstRank.get(j);
					if(avg <= r.getMax() && avg >= r.getMin()) {
						rt.setRank(r.getName());
					}
				}
				lstRT.add(rt);
			}
	    }
		tDAO.close();
		sDAO.close();
		mDAO.close();
		return sort(lstRT);
	}
	
	/**
	 * Creem les estadistiques inicials
	 */
	public void createInitialStatistics() throws Exception {
		StatisticsDAO sDAO = new StatisticsDAO();
		
		sDAO.addStatistics("PTS", "Punts totals");
		sDAO.addStatistics("1QP", "Punts al primer quart");
		sDAO.addStatistics("2QP", "Punts al segon quart");
		sDAO.addStatistics("3QP", "Punts al tercer quart");
		sDAO.addStatistics("4QP", "Punts al quart quart");
		sDAO.addStatistics("REB", "Rebots totals");
		sDAO.addStatistics("OREB", "Rebots ofensius");
		sDAO.addStatistics("DREB", "Rebots defensius");
		
		sDAO.close();
	}
	
	private static float round2(float number) {
	    int pow = 100;
	    float tmp = number * pow;
	    return ( (float) ( (int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp) ) ) / pow;
	}
	
	public void createRanks(int idRating) throws Exception {
		StatisticsDAO sDAO = new StatisticsDAO();
		TeamDAO tDAO = new TeamDAO();
		List<RankTeam> lstRT = getAllRankTeams(idRating);
		RankTeam rt = new RankTeam();
		Rank r = new Rank();
		List<Rank> lstR = new ArrayList<Rank>();
		List<RankTeam> lstRankA = new ArrayList<RankTeam>();
		float minA = 0;
		List<RankTeam> lstRankB = new ArrayList<RankTeam>();
		float minB = 0;
		List<RankTeam> lstRankC = new ArrayList<RankTeam>();

		for(int i = 0; i < lstRT.size(); i++) {
			rt = lstRT.get(i);
			if(i < 10)
				lstRankA.add(rt);
			else
				if(i < 20)
					lstRankB.add(rt);
				else
					lstRankC.add(rt);
			if(i == 9)
				minA = rt.getAvg();
			if(i == 19)
				minB = rt.getAvg();
		}
		sDAO.addRank(idRating, "BOT", "BOT", 0, minB);
		lstR = sDAO.getRanksRating(idRating);
		r = lstR.get(lstR.size() -1);
		for(int i = 0; i < lstRankC.size(); i++) {
			rt = lstRankC.get(i);
			tDAO.addTeamRank(rt.getIdTeam(),r.getId());
		}
		sDAO.addRank(idRating, "MID", "MID", minB, minA);
		lstR = sDAO.getRanksRating(idRating);
		r = lstR.get(lstR.size() -1);
		for(int i = 0; i < lstRankB.size(); i++) {
			rt = lstRankB.get(i);
			tDAO.addTeamRank(rt.getIdTeam(),r.getId());
		}
		sDAO.addRank(idRating, "TOP", "TOP", minA, 200);
		lstR = sDAO.getRanksRating(idRating);
		r = lstR.get(lstR.size() -1);
		for(int i = 0; i < lstRankA.size(); i++) {
			rt = lstRankA.get(i);
			tDAO.addTeamRank(rt.getIdTeam(),r.getId());
		}
		
		sDAO.close();
		tDAO.close();
	}
	
	public List<RankTeam> sort(List<RankTeam> lst){
		
		Collections.sort(lst, new Comparator<RankTeam>() {
			@Override
			public int compare(RankTeam r1, RankTeam r2) {
				if(r1.getAvg() < r2.getAvg())
					return 1;
				else
					return -1;
			}
		});
		
		return lst;
		
	}
}
