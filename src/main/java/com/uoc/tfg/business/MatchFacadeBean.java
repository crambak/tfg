package com.uoc.tfg.business;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.uoc.tfg.dao.MatchDAO;
import com.uoc.tfg.dao.StatisticsDAO;
import com.uoc.tfg.dao.TeamDAO;
import com.uoc.tfg.model.auxiliar.GameTeam;
import com.uoc.tfg.model.auxiliar.MatchPointsStats;
import com.uoc.tfg.model.auxiliar.MatchReboundsStats;
import com.uoc.tfg.model.auxiliar.MatchTeam;
import com.uoc.tfg.model.auxiliar.RatingList;
import com.uoc.tfg.model.auxiliar.Stats;
import com.uoc.tfg.model.auxiliar.StatsRating;
import com.uoc.tfg.model.auxiliar.StatsRatingMatch;
import com.uoc.tfg.model.bd.Match;
import com.uoc.tfg.model.bd.Rank;
import com.uoc.tfg.model.bd.Rating;
import com.uoc.tfg.model.bd.Statistics;
import com.uoc.tfg.model.bd.StatisticsMatch;
import com.uoc.tfg.model.rest.BoxScore;
import com.uoc.tfg.model.rest.Conf;
import com.uoc.tfg.model.rest.Conference;
import com.uoc.tfg.model.rest.ConferenceTeamStandings;
import com.uoc.tfg.model.rest.Full;
import com.uoc.tfg.model.rest.Fullgameschedule;
import com.uoc.tfg.model.rest.Game;
import com.uoc.tfg.model.rest.GameBoxScore;
import com.uoc.tfg.model.rest.GameEntry;
import com.uoc.tfg.model.rest.Quarter;
import com.uoc.tfg.model.rest.QuarterSummary;
import com.uoc.tfg.model.rest.Team;
import com.uoc.tfg.model.rest.TeamEntry;
import com.uoc.tfg.model.rest.TeamStats;

public class MatchFacadeBean implements MatchFacade {

	/**
	 * Es retorna la informació d'un partit en concret
	 */
	public Match getMatch(int idMatch) throws Exception {
		MatchDAO mDAO = new MatchDAO();
		Match m =  mDAO.getMatch(idMatch);
		mDAO.close();
		return m;
	}

	/**
	 * Es fa la crida a la api extern per tal d'obtenir la informació dels equips i del calendari
	 * @throws Exception 
	 */
	public void getFullGameSchedule() throws Exception {

		StatisticsFacadeBean sBean = new StatisticsFacadeBean();
		sBean.createInitialStatistics();
		Client client = Client.create();
		String url = "https://api.mysportsfeeds.com/v1.0/pull/nba/2019-2020-regular/conference_team_standings.json?teamstats=W,L";
		String authStringEnc = "ZmQxNjVjOWQtYzFhYy00MTRhLWExNzAtMzkzNzc0OkhlbGxv";
		WebResource webResource = client.resource(url);
		String resp = webResource.accept("application/json").header("Authorization", "Basic " + authStringEnc).get(String.class);
		resp = resp.replace("\"ID\"", "\"id\"");
		resp = resp.replace("\"City\"", "\"city\"");
		resp = resp.replace("\"Name\"", "\"name\"");
		resp = resp.replace("\"Abbreviation\"", "\"abbreviation\"");
		resp = resp.replace("\"GamesPlayed\"", "\"gamesplayed\"");
		resp = resp.replace("\"Wins\"", "\"wins\"");
		resp = resp.replace("\"Losses\"", "\"losses\"");
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Conf c = mapper.readValue(resp, Conf.class);
		
		TeamDAO tDAO = new TeamDAO();
		ConferenceTeamStandings cts = c.getConferenceTeamStandings();
		Conference conference = cts.getConference().get(0);
		TeamEntry te = new TeamEntry();
		Team t = new Team();
		for(int i = 0; i < conference.getTeamentry().size(); i++) {
			te = conference.getTeamentry().get(i);
			t = te.getTeam();
			if(tDAO.getTeam(t.getId()) == null) {
				tDAO.addTeam(t.getId(), t.getName(), t.getCity(), t.getAbbreviation(), "E");
				System.out.println("Insert Team East: "+ t.getCity()+" "+t.getName());
			}
		}
		conference = cts.getConference().get(1);
		for(int i = 0; i < conference.getTeamentry().size(); i++) {
			te = conference.getTeamentry().get(i);
			t = te.getTeam();
			if(tDAO.getTeam(t.getId()) == null) {
				tDAO.addTeam(t.getId(), t.getName(), t.getCity(), t.getAbbreviation(), "W");
				System.out.println("Insert Team West: "+ t.getCity()+" "+t.getName());
			}
		}
		
		client = Client.create();
		url = "https://api.mysportsfeeds.com/v1.0/pull/nba/2019-2020-regular/full_game_schedule.json";
		
		webResource = client.resource(url);
		resp = webResource.accept("application/json").header("Authorization", "Basic " + authStringEnc).get(String.class);
		resp = resp.replace("\"ID\"", "\"id\"");
		resp = resp.replace("\"City\"", "\"city\"");
		resp = resp.replace("\"Name\"", "\"name\"");
		resp = resp.replace("\"Abbreviation\"", "\"abbreviation\"");
		resp = resp.replace("#text", "text");
		
		mapper = new ObjectMapper();
		Full f = mapper.readValue(resp, Full.class);
		
		
		
		Fullgameschedule fgs = f.getFullgameschedule();
		
		
		MatchDAO mDAO = new MatchDAO(); 
		GameEntry ge = new GameEntry();
		Team away = new Team();
		Team home = new Team();
		for(int i = 0; i<fgs.getGameentry().size(); i++) {
			ge = fgs.getGameentry().get(i);
			if(mDAO.getMatch(ge.getId()) == null) {
				away = ge.getAwayTeam();
				home = ge.getHomeTeam();
				java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(ge.getDate());
				Date d = new java.sql.Date(utilDate.getTime());
				mDAO.addMatch(ge.getId(), away.getId(), home.getId(), d, "2019-2020-regular", ge.getLocation());
				System.out.println("Insert Match between: "+away.getName()+" vs "+home.getName());
			}
		}
		tDAO.close();
		mDAO.close();
		
	}

	/**
	 * Es fa la crida per obtenir totes les estadístiques dels partits d'un dia en concret
	 */
	public void getDayGamelogs(Date date) throws Exception {

		Match m = new Match();
		MatchDAO mDAO = new MatchDAO();
		StatisticsDAO sDAO = new StatisticsDAO();
		List<Match> lstMatch = new ArrayList<Match>();
		List<RatingList> lstRat = new ArrayList<RatingList>();
		List<Rank> lstR = new ArrayList<Rank>();
		RatingList ratList = new RatingList();
		Rating rat = new Rating();
		lstMatch = mDAO.getDayMatchs(date);
		
		for(int i = 0; i < lstMatch.size(); i++) {
			m = lstMatch.get(i);
			importMatch(m.getId());
		}
		TeamFacadeBean tf = new TeamFacadeBean();
		StatisticsFacadeBean sf = new StatisticsFacadeBean();
		lstRat = sf.getAllRatings();
		for(int i = 0; i < lstRat.size(); i++) {
			ratList = lstRat.get(i);
			rat = sDAO.getRating(ratList.getId());
			if(rat.isActive()) {
				if(rat.isAutomatic()) {
					lstR = sDAO.getRanksRating(rat.getId());
					for(int j = 0; j < lstR.size(); j++) {
						sDAO.deleteRank(lstR.get(j).getId());
					}
					sf.createRanks(rat.getId());
				} else {
					tf.calculateRanks(date, rat);
				}
			}
		}
		
		mDAO.close();
		sDAO.close();
	}

	/**
	 * Es fa la crida per obtenir totes les estadístiques dels partits jugats en un rang de dates
	 */
	public void getPastGamelogs(Date dateIni, Date dateFin) throws Exception {
		Match m = new Match();
		MatchDAO mDAO = new MatchDAO();
		StatisticsDAO sDAO = new StatisticsDAO();
		List<Match> lstMatch = new ArrayList<Match>();
		lstMatch = mDAO.getDayMatchs(dateIni, dateFin);
		List<RatingList> lstRat = new ArrayList<RatingList>();
		List<Rank> lstR = new ArrayList<Rank>();
		RatingList ratList = new RatingList();
		Rating rat = new Rating();
		
		for(int i = 0; i < lstMatch.size(); i++) {
			m = lstMatch.get(i);
			System.out.println("Importing match: "+m.getId());
			importMatch(m.getId());
		}
		TeamFacadeBean tf = new TeamFacadeBean();
		StatisticsFacadeBean sf = new StatisticsFacadeBean();
		lstRat = sf.getAllRatings();
		for(int i = 0; i < lstRat.size(); i++) {
			ratList = lstRat.get(i);
			rat = sDAO.getRating(ratList.getId());
			if(rat.isActive()) {
				if(rat.isAutomatic()) {
					lstR = sDAO.getRanksRating(rat.getId());
					for(int j = 0; j < lstR.size(); j++) {
						sDAO.deleteRank(lstR.get(j).getId());
					}
					sf.createRanks(rat.getId());
				} else {
					tf.calculateRanks(dateFin, rat);
				}
			}
		}
		mDAO.close();
		sDAO.close();
		
	}
	
	/**
	 * Aqui centralitzem totes les crides per obtenir les dades dels partits
	 */
	public void importMatch(int idMatch) throws Exception {
		
		StatisticsDAO sDAO = new StatisticsDAO();
		MatchDAO mDAO = new MatchDAO();
		TeamDAO tDAO = new TeamDAO();
		List<StatisticsMatch> lstSt = mDAO.getStatisticsMatch(idMatch);
		if(lstSt.size() == 0) {
		
			Client client = Client.create();
			String url = "https://api.mysportsfeeds.com/v1.0/pull/nba/2019-2020-regular/game_boxscore.json?gameid="+idMatch+"&teamstats=OREB,DREB,REB&playerstats=none";
			String authStringEnc = "ZmQxNjVjOWQtYzFhYy00MTRhLWExNzAtMzkzNzc0OkhlbGxv";
			WebResource webResource = client.resource(url);
			String resp = webResource.accept("application/json").header("Authorization", "Basic " + authStringEnc).get(String.class);
			resp = resp.replace("\"ID\"", "\"id\"");
			resp = resp.replace("\"City\"", "\"city\"");
			resp = resp.replace("\"Name\"", "\"name\"");
			resp = resp.replace("\"Abbreviation\"", "\"abbreviation\"");
			resp = resp.replace("\"GamesPlayed\"", "\"gamesplayed\"");
			resp = resp.replace("\"Wins\"", "\"wins\"");
			resp = resp.replace("\"Losses\"", "\"losses\"");
			resp = resp.replace("#text", "text");
			resp = resp.replace("\"OffReb\"", "\"offreb\"");
			resp = resp.replace("\"DefReb\"", "\"defreb\"");
			resp = resp.replace("\"Reb\"", "\"reb\"");
			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			BoxScore box = mapper.readValue(resp, BoxScore.class);
			
			List<Statistics> lstSta = new ArrayList<>();
			Statistics st = new Statistics();
			lstSta = sDAO.getAllStatistics();
			GameBoxScore gbs = box.getGameboxscore();
			Game g = gbs.getGame();
			QuarterSummary qs = gbs.getQuarterSummary();
			Quarter qTotal = qs.getQuarterTotals();
			Quarter q1 = qs.getQuarter().get(0);
			Quarter q2 = qs.getQuarter().get(1);
			Quarter q3 = qs.getQuarter().get(2);
			Quarter q4 = qs.getQuarter().get(3);
			TeamStats ats = gbs.getAwayTeam().getAwayTeamStats();
			TeamStats hts = gbs.getHomeTeam().getHomeTeamStats();
			com.uoc.tfg.model.bd.Team at = new com.uoc.tfg.model.bd.Team();
			com.uoc.tfg.model.bd.Team ht = new com.uoc.tfg.model.bd.Team();
			
			for(int i = 0; i < lstSta.size(); i++) {
				st = lstSta.get(i);
				switch (st.getType()) {
				case "PTS":
					mDAO.addStatisticsMatch(idMatch, st.getId(), qTotal.getAwayScore(), qTotal.getHomeScore());
					break;
				case "1QP":
					mDAO.addStatisticsMatch(idMatch, st.getId(), q1.getAwayScore(), q1.getHomeScore());
					break;
				case "2QP":
					mDAO.addStatisticsMatch(idMatch, st.getId(), q2.getAwayScore(), q2.getHomeScore());
					break;
				case "3QP":
					mDAO.addStatisticsMatch(idMatch, st.getId(), q3.getAwayScore(), q3.getHomeScore());
					break;
				case "4QP":
					mDAO.addStatisticsMatch(idMatch, st.getId(), q4.getAwayScore(), q4.getHomeScore());
					break;
				case "REB":
					mDAO.addStatisticsMatch(idMatch, st.getId(), ats.getReb().getText(), hts.getReb().getText());
					break;
				case "OREB":
					mDAO.addStatisticsMatch(idMatch, st.getId(), ats.getOffreb().getText(), hts.getOffreb().getText());
					break;
				case "DREB":
					mDAO.addStatisticsMatch(idMatch, st.getId(), ats.getDefreb().getText(), hts.getDefreb().getText());
					break;
				}
			}
			at = tDAO.getTeam(g.getAwayTeam().getId());
			ht = tDAO.getTeam(g.getHomeTeam().getId());
			if(qTotal.getAwayScore() > qTotal.getHomeScore()) {
				tDAO.updateTeam(at.getId(), at.getWins()+1, at.getLoses());
				tDAO.updateTeam(ht.getId(), ht.getWins(), ht.getLoses()+1);
			} else {
				tDAO.updateTeam(at.getId(), at.getWins(), at.getLoses()+1);
				tDAO.updateTeam(ht.getId(), ht.getWins()+1, ht.getLoses());
			}
		}
		mDAO.close();
		sDAO.close();
		tDAO.close();
	}

	
	/**
	 * Retornem els partits del dia
	 */
	public List<com.uoc.tfg.model.auxiliar.Game> getGames(Date day) throws Exception {
		
		MatchDAO mDAO = new MatchDAO();
		TeamDAO tDAO = new TeamDAO();
		StatisticsDAO sDAO = new StatisticsDAO();
		List<com.uoc.tfg.model.auxiliar.Game> lstGame = new ArrayList<com.uoc.tfg.model.auxiliar.Game>();
		List<Match> lstMatch = mDAO.getDayMatchs(day);
		Match match = new Match();
		com.uoc.tfg.model.bd.Team awayTeam = new com.uoc.tfg.model.bd.Team();
		com.uoc.tfg.model.bd.Team homeTeam = new com.uoc.tfg.model.bd.Team();
		com.uoc.tfg.model.auxiliar.Game game = new com.uoc.tfg.model.auxiliar.Game();
		List<StatisticsMatch> lstSM = new ArrayList<>();
		StatisticsMatch SM = new StatisticsMatch();
		List<Statistics> lstSt = sDAO.getAllStatistics();
		Statistics st = new Statistics();
		for(int i = 0; i < lstSt.size(); i++) {
			st = lstSt.get(i);
			if("PTS".equals(st.getType()))
				break;
		}
		
		for(int i = 0; i < lstMatch.size(); i++) {
			game = new com.uoc.tfg.model.auxiliar.Game();
			match = lstMatch.get(i);
			awayTeam = tDAO.getTeam(match.getIdAwayTeam());
			homeTeam = tDAO.getTeam(match.getIdHomeTeam());
			game.setId(match.getId());
			game.setIdAwayTeam(awayTeam.getId());
			game.setAwayTeamName(awayTeam.getCity()+" "+awayTeam.getName());
			game.setIdHomeTeam(homeTeam.getId());
			game.setHomeTeamName(homeTeam.getCity()+" "+homeTeam.getName());
			lstSM = mDAO.getStatisticsMatch(match.getId());
			
			for(int j = 0; j < lstSM.size(); j++) {
				SM = lstSM.get(j);
				if(SM.getIdStatistics() == st.getId()) {
					game.setResult(SM.getAwayTeam()+" - "+SM.getHomeTeam());
					break;
				}
			}
			lstGame.add(game);
		}
		
		mDAO.close();
		tDAO.close();
		sDAO.close();
		return lstGame;
	}
	
	/**
	 * Obtenim les dades dels n darrers partits d'un equip
	 */
	public List<GameTeam> getLastGames(int idTeam, int num) throws Exception {

		MatchDAO mDAO = new MatchDAO();
		TeamDAO tDAO = new TeamDAO();
		StatisticsDAO sDAO = new StatisticsDAO();
		List<GameTeam> lstGT = new ArrayList<GameTeam>();
		GameTeam gt = new GameTeam();
		Match m = new Match();
		com.uoc.tfg.model.bd.Team t = new com.uoc.tfg.model.bd.Team();
		List<StatisticsMatch> lstSM = new ArrayList<>();
		StatisticsMatch sm = new StatisticsMatch();
		List<Match> lstMatch = mDAO.getLastGames(idTeam, num);
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		int homePoints = 0;
		int awayPoints = 0;
		List<Statistics> lstSt = sDAO.getAllStatistics();
		Statistics st = new Statistics();
		for(int i = 0; i < lstSt.size(); i++) {
			st = lstSt.get(i);
			if("PTS".equals(st.getType()))
				break;
		}
		
		for(int i = 0; i < lstMatch.size(); i++) {
			gt = new GameTeam();
			m = lstMatch.get(i);
			
			lstSM = mDAO.getStatisticsMatch(m.getId());
			for(int j = 0; j < lstSM.size(); j++) {
				sm = lstSM.get(j);
				if(sm.getIdStatistics() == st.getId()) {
					homePoints = sm.getHomeTeam();
					awayPoints = sm.getAwayTeam();
					break;
				}
			}
			gt.setId(m.getId());
			gt.setDate(df.format(m.getDate()));
			if(m.getIdAwayTeam() == idTeam) {
				gt.setLocation("F");
				t = tDAO.getTeam(m.getIdHomeTeam());
				gt.setOpponent(t.getCity()+" "+t.getName());
				gt.setIdOpponent(t.getId());
				if(awayPoints > homePoints)
					gt.setResult("V "+awayPoints+"-"+homePoints);
				else
					gt.setResult("D "+awayPoints+"-"+homePoints);
			} else {
				gt.setLocation("C");
				t = tDAO.getTeam(m.getIdAwayTeam());
				gt.setOpponent(t.getCity()+" "+t.getName());
				gt.setIdOpponent(t.getId());
				if(awayPoints > homePoints)
					gt.setResult("D "+awayPoints+"-"+homePoints);
				else
					gt.setResult("V "+awayPoints+"-"+homePoints);
			}
			lstGT.add(gt);
		}
		
		mDAO.close();
		tDAO.close();
		sDAO.close();
		return lstGT;
	}
	
	/**
	 * Obtenim les dades dels n propers partits d'un equip
	 */
	public List<GameTeam> getNextGames(int idTeam, int num) throws Exception {
		
		MatchDAO mDAO = new MatchDAO();
		TeamDAO tDAO = new TeamDAO();
		List<GameTeam> lstGT = new ArrayList<GameTeam>();
		GameTeam gt = new GameTeam();
		Match m = new Match();
		com.uoc.tfg.model.bd.Team t = new com.uoc.tfg.model.bd.Team();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		List<Match> lstMatch = mDAO.getNextGames(idTeam, num);
		
		for(int i = 0; i < lstMatch.size(); i++) {

			gt = new GameTeam();
			m = lstMatch.get(i);
			gt.setId(m.getId());
			gt.setDate(df.format(m.getDate()));
			if(m.getIdAwayTeam() == idTeam) {
				gt.setLocation("F");
				t = tDAO.getTeam(m.getIdHomeTeam());
				gt.setOpponent(t.getCity()+" "+t.getName());
				gt.setIdOpponent(t.getId());
			} else {
				gt.setLocation("C");
				t = tDAO.getTeam(m.getIdAwayTeam());
				gt.setOpponent(t.getCity()+" "+t.getName());
				gt.setIdOpponent(t.getId());
			}
			lstGT.add(gt);
		}
		mDAO.close();
		tDAO.close();
		return lstGT;
	}
	
	/**
	 * Obtenim les dades del partit d'un equip
	 */
	public MatchTeam getMatchTeam(int idTeam) throws Exception {
		
		MatchTeam mt = new MatchTeam();
		TeamDAO tDAO = new TeamDAO();
		com.uoc.tfg.model.bd.Team t = new com.uoc.tfg.model.bd.Team();
		t = tDAO.getTeam(idTeam);
		mt.setId(idTeam);
		mt.setName(t.getCity()+" "+t.getName()+" "+t.getAbbreviation());
		mt.setAbbreviation(t.getAbbreviation());
		mt.setBalance(t.getWins()+"/"+t.getLoses());
		TeamFacadeBean tf = new TeamFacadeBean();
		mt.setPosition(tf.getPosition(t));
		
		
		tDAO.close();
		
		return mt;
	}
	
	/**
	 * Obtenim les dades del punts d'un partit d'un equip
	 */
	public List<MatchPointsStats> getMatchPoints(int idMatch) throws Exception{
		
		MatchPointsStats home = new MatchPointsStats();
		MatchPointsStats away = new MatchPointsStats();
		MatchDAO mDAO = new MatchDAO();
		StatisticsDAO sDAO = new StatisticsDAO();
		TeamDAO tDAO = new TeamDAO();
		Match m = mDAO.getMatch(idMatch);
		com.uoc.tfg.model.bd.Team homeTeam = tDAO.getTeam(m.getIdHomeTeam());
		com.uoc.tfg.model.bd.Team awayTeam = tDAO.getTeam(m.getIdAwayTeam());
		List<MatchPointsStats> lstMPS = new ArrayList<MatchPointsStats>();
		List<StatisticsMatch> lstSM = new ArrayList<StatisticsMatch>();
		StatisticsMatch sm = new StatisticsMatch();
		List<Statistics> lstSt = new ArrayList<Statistics>();
		Statistics s = new Statistics();
		lstSM = mDAO.getStatisticsMatch(idMatch);
		lstSt = sDAO.getAllStatistics();
		
		for(int i = 0; i < lstSM.size(); i++) {
			sm = lstSM.get(i);
			for(int j = 0; j < lstSt.size(); j++) {
				s = lstSt.get(j);
				if(s.getId() == sm.getIdStatistics()) {
					if("1QP".equals(s.getType())) {
						home.setFirst(sm.getHomeTeam());
						away.setFirst(sm.getAwayTeam());
					}
					if("2QP".equals(s.getType())) {
						home.setSecond(sm.getHomeTeam());
						away.setSecond(sm.getAwayTeam());
					}
					if("3QP".equals(s.getType())) {
						home.setThird(sm.getHomeTeam());
						away.setThird(sm.getAwayTeam());
					}
					if("4QP".equals(s.getType())) {
						home.setFourth(sm.getHomeTeam());
						away.setFourth(sm.getAwayTeam());
					}
					if("PTS".equals(s.getType())) {
						home.setTotal(sm.getHomeTeam());
						away.setTotal(sm.getAwayTeam());
					}
				}
			}
		}
		
		away.setIdTeam(awayTeam.getId());
		away.setTeam(awayTeam.getCity()+" "+awayTeam.getName());
		home.setIdTeam(homeTeam.getId());
		home.setTeam(homeTeam.getCity()+" "+homeTeam.getName());
		
		lstMPS.add(home);
		lstMPS.add(away);
		
		mDAO.close();
		sDAO.close();
		tDAO.close();
		
		return lstMPS;
	}
	
	/**
	 * Obtenim les dades del rebot d'un partit d'un equip
	 */
	public List<MatchReboundsStats> getMatchRebounds(int idMatch) throws Exception{
		
		MatchReboundsStats home = new MatchReboundsStats();
		MatchReboundsStats away = new MatchReboundsStats();
		MatchDAO mDAO = new MatchDAO();
		StatisticsDAO sDAO = new StatisticsDAO();
		TeamDAO tDAO = new TeamDAO();
		Match m = mDAO.getMatch(idMatch);
		com.uoc.tfg.model.bd.Team homeTeam = tDAO.getTeam(m.getIdHomeTeam());
		com.uoc.tfg.model.bd.Team awayTeam = tDAO.getTeam(m.getIdAwayTeam());
		List<MatchReboundsStats> lstMRS = new ArrayList<MatchReboundsStats>();
		List<StatisticsMatch> lstSM = new ArrayList<StatisticsMatch>();
		StatisticsMatch sm = new StatisticsMatch();
		List<Statistics> lstSt = new ArrayList<Statistics>();
		Statistics s = new Statistics();
		lstSM = mDAO.getStatisticsMatch(idMatch);
		lstSt = sDAO.getAllStatistics();
		
		for(int i = 0; i < lstSM.size(); i++) {
			sm = lstSM.get(i);
			for(int j = 0; j < lstSt.size(); j++) {
				s = lstSt.get(j);
				if(s.getId() == sm.getIdStatistics()) {
					if("OREB".equals(s.getType())) {
						home.setOffensive(sm.getHomeTeam());
						away.setOffensive(sm.getAwayTeam());
					}
					if("DREB".equals(s.getType())) {
						home.setDefensive(sm.getHomeTeam());
						away.setDefensive(sm.getAwayTeam());
					}
					if("REB".equals(s.getType())) {
						home.setTotal(sm.getHomeTeam());
						away.setTotal(sm.getAwayTeam());
					}
				}
			}
		}
		away.setIdTeam(awayTeam.getId());
		away.setTeam(awayTeam.getCity()+" "+awayTeam.getName());
		home.setIdTeam(homeTeam.getId());
		home.setTeam(homeTeam.getCity()+" "+homeTeam.getName());

		lstMRS.add(home);
		lstMRS.add(away);
		
		mDAO.close();
		sDAO.close();
		tDAO.close();
		
		return lstMRS;
	}
	
	/**
	 * Obtenim les dades d'una estadística d'un partit
	 */
	public List<Stats> getStats(int idMatch, int idStats) throws Exception {
		
		List<Stats> lstStats = new ArrayList<Stats>();
		Stats statsH = new Stats();
		Stats statsHO = new Stats();
		Stats statsA = new Stats();
		Stats statsAO = new Stats();
		MatchDAO mDAO = new MatchDAO();
		TeamDAO tDAO = new TeamDAO();
		Date d = mDAO.getMaxDate();
		Match m = mDAO.getMatch(idMatch);
		com.uoc.tfg.model.bd.Team tH = tDAO.getTeam(m.getIdHomeTeam());
		com.uoc.tfg.model.bd.Team tA = tDAO.getTeam(m.getIdAwayTeam());
		statsH.setName(tH.getAbbreviation());
		statsHO.setName(tH.getAbbreviation()+" Opp");
		statsA.setName(tA.getAbbreviation());
		statsAO.setName(tA.getAbbreviation()+" Opp");
		Calendar cal = GregorianCalendar.getInstance();
	    cal.setTime(d);
	    cal.add(GregorianCalendar.DATE, -365);
	    Date date = new Date(cal.getTimeInMillis());
	    //All
		statsH.setAll(mDAO.getTeamMatchStatistics(tH.getId(), idStats, 82, date, "All", false));
		statsHO.setAll(mDAO.getTeamMatchStatistics(tH.getId(), idStats, 82, date, "All", true));
		statsA.setAll(mDAO.getTeamMatchStatistics(tA.getId(), idStats, 82, date, "All", false));
		statsAO.setAll(mDAO.getTeamMatchStatistics(tA.getId(), idStats, 82, date, "All", true));
		//Last 10
		statsH.setLast10(mDAO.getTeamMatchStatistics(tH.getId(), idStats, 10, date, "All", false));
		statsHO.setLast10(mDAO.getTeamMatchStatistics(tH.getId(), idStats, 10, date, "All", true));
		statsA.setLast10(mDAO.getTeamMatchStatistics(tA.getId(), idStats, 10, date, "All", false));
		statsAO.setLast10(mDAO.getTeamMatchStatistics(tA.getId(), idStats, 10, date, "All", true));
		//Last 5
		statsH.setLast5(mDAO.getTeamMatchStatistics(tH.getId(), idStats, 5, date, "All", false));
		statsHO.setLast5(mDAO.getTeamMatchStatistics(tH.getId(), idStats, 5, date, "All", true));
		statsA.setLast5(mDAO.getTeamMatchStatistics(tA.getId(), idStats, 5, date, "All", false));
		statsAO.setLast5(mDAO.getTeamMatchStatistics(tA.getId(), idStats, 5, date, "All", true));
		//Last
		statsH.setLast(mDAO.getTeamMatchStatistics(tH.getId(), idStats, 1, date, "All", false));
		statsHO.setLast(mDAO.getTeamMatchStatistics(tH.getId(), idStats, 1, date, "All", true));
		statsA.setLast(mDAO.getTeamMatchStatistics(tA.getId(), idStats, 1, date, "All", false));
		statsAO.setLast(mDAO.getTeamMatchStatistics(tA.getId(), idStats, 1, date, "All", true));
		//Home/Away
		statsH.setHome(mDAO.getTeamMatchStatistics(tH.getId(), idStats, 82, date, "Home", false));
		statsHO.setHome(mDAO.getTeamMatchStatistics(tH.getId(), idStats, 82, date, "Home", true));
		statsA.setHome(mDAO.getTeamMatchStatistics(tA.getId(), idStats, 82, date, "Away", false));
		statsAO.setHome(mDAO.getTeamMatchStatistics(tA.getId(), idStats, 82, date, "Away", true));
		//Last 15 days
		cal.setTime(d);
	    cal.add(GregorianCalendar.DATE, -15);
	    date = new Date(cal.getTimeInMillis());
		statsH.setLast15(mDAO.getTeamMatchStatistics(tH.getId(), idStats, 82, date, "All", false));
		statsHO.setLast15(mDAO.getTeamMatchStatistics(tH.getId(), idStats, 82, date, "All", true));
		statsA.setLast15(mDAO.getTeamMatchStatistics(tA.getId(), idStats, 82, date, "All", false));
		statsAO.setLast15(mDAO.getTeamMatchStatistics(tA.getId(), idStats, 82, date, "All", true));
		//Last 30 days
		cal.setTime(d);
	    cal.add(GregorianCalendar.DATE, -30);
	    date = new Date(cal.getTimeInMillis());
		statsH.setLast30(mDAO.getTeamMatchStatistics(tH.getId(), idStats, 82, date, "All", false));
		statsHO.setLast30(mDAO.getTeamMatchStatistics(tH.getId(), idStats, 82, date, "All", true));
		statsA.setLast30(mDAO.getTeamMatchStatistics(tA.getId(), idStats, 82, date, "All", false));
		statsAO.setLast30(mDAO.getTeamMatchStatistics(tA.getId(), idStats, 82, date, "All", true));
		//Last 30 days
		cal.setTime(d);
	    cal.add(GregorianCalendar.DATE, -7);
	    date = new Date(cal.getTimeInMillis());
		statsH.setLast7(mDAO.getTeamMatchStatistics(tH.getId(), idStats, 82, date, "All", false));
		statsHO.setLast7(mDAO.getTeamMatchStatistics(tH.getId(), idStats, 82, date, "All", true));
		statsA.setLast7(mDAO.getTeamMatchStatistics(tA.getId(), idStats, 82, date, "All", false));
		statsAO.setLast7(mDAO.getTeamMatchStatistics(tA.getId(), idStats, 82, date, "All", true));
		
		lstStats.add(statsH);
		lstStats.add(statsHO);
		lstStats.add(statsA);
		lstStats.add(statsAO);
		
		mDAO.close();
		tDAO.close();
		return lstStats;
	}
	
	/**
	 * Obtenim la data del darrer partit del qual tenim estadístiques
	 */
	public Date getLastDateMatch() throws Exception {
		MatchDAO mDAO = new MatchDAO();
		Date d = mDAO.getMaxDate();
		if(d==null){
			d = mDAO.getMinDate();
			if(d==null) {
				mDAO.close();
				return null;
			}
			Calendar calendar = Calendar.getInstance();
	        calendar.setTime(d); 
	        calendar.add(Calendar.DAY_OF_YEAR, -1);
			d = new Date(calendar.getTimeInMillis());
			
		}
		mDAO.close();
		return d;
	}
	
	/**
	 * Obtenim les dades d'un rànquing concret d'un partit
	 */
	public List<StatsRating> getStatsRating(int idMatch, int idRating, float stakeHome, float stakeAway, float stakeTotal) throws Exception {
		List<StatsRating> lstSR = new ArrayList<StatsRating>();
		StatsRating srHome = new StatsRating();
		StatsRating srAway = new StatsRating();		
		StatsRating srAux = new StatsRating();
		MatchDAO mDAO = new MatchDAO();
		TeamDAO tDAO = new TeamDAO();
		StatisticsDAO sDAO = new StatisticsDAO();
		Rating rat = sDAO.getRating(idRating);
		Match m = mDAO.getMatch(idMatch);
		com.uoc.tfg.model.bd.Team tH = tDAO.getTeam(m.getIdHomeTeam());
		com.uoc.tfg.model.bd.Team tA = tDAO.getTeam(m.getIdAwayTeam());
		Rank rankH = tDAO.getTeamRank(tH.getId(), idRating);
		Rank rankA = tDAO.getTeamRank(tA.getId(), idRating);
		srHome.setName(tH.getAbbreviation());
		srAway.setName(tA.getAbbreviation());
		srHome.setRank(rankH.getName());
		srAway.setRank(rankA.getName());
		srHome.setOppRank(rankA.getName());
		srAway.setOppRank(rankH.getName());
		srAux = mDAO.getTeamMatchStatisticsVsRank(tH.getId(), rat.getIdStatistics(), rat.getLocation(), rankA.getId());
		srHome.setAvg(srAux.getAvg());
		srHome.setMin(srAux.getMin());
		srHome.setMax(srAux.getMax());
		srHome.setLast(srAux.getLast());
		srHome.setLstStats(calculateStakes(srAux.getLstStats(), stakeHome, stakeAway, stakeTotal));
		srAux = mDAO.getTeamMatchStatisticsVsRank(tA.getId(), rat.getIdStatistics(), rat.getLocation(), rankH.getId());
		srAway.setAvg(srAux.getAvg());
		srAway.setMin(srAux.getMin());
		srAway.setMax(srAux.getMax());
		srAway.setLast(srAux.getLast());
		srAway.setLstStats(calculateStakes(srAux.getLstStats(), stakeAway, stakeHome, stakeTotal));
		
		lstSR.add(srHome);
		lstSR.add(srAway);
		
		mDAO.close();
		tDAO.close();
		sDAO.close();
		return lstSR;
	}
	
	public List<StatsRatingMatch> calculateStakes(List<StatsRatingMatch> sr, float stakeHome, float stakeAway, float stakeTotal) throws Exception {
		
		List<StatsRatingMatch> lstSr = new ArrayList<StatsRatingMatch>();
		StatsRatingMatch srm = new StatsRatingMatch();
		float min = 0;
		float max = 0;
		for(int i = 0; i < sr.size(); i++) {
			srm = sr.get(i);
			if(stakeHome > 0) {
				min = stakeHome * new Float("0.95");
				max = stakeHome * new Float("1.05");
				if(srm.getTeamStat() < min)
					srm.setColourTeam("background-color:red");
				else
					if(srm.getTeamStat() > max)
						srm.setColourTeam("background-color:green");
					else
						srm.setColourTeam("background-color:yellow");
			}
			if(stakeAway > 0) {
				min = stakeAway * new Float("0.95");
				max = stakeAway * new Float("1.05");
				if(srm.getOppStat() < min)
					srm.setColourOpp("background-color:red");
				else
					if(srm.getOppStat() > max)
						srm.setColourOpp("background-color:green");
					else
						srm.setColourOpp("background-color:yellow");
			}
			if(stakeTotal > 0) {
				min = stakeTotal * new Float("0.95");
				max = stakeTotal * new Float("1.05");
				if(srm.getSumStat() < min)
					srm.setColourSum("background-color:red");
				else
					if(srm.getSumStat() > max)
						srm.setColourSum("background-color:green");
					else
						srm.setColourSum("background-color:yellow");
			}
			lstSr.add(srm);
		}
		
		
		return lstSr;
	}
	
}
