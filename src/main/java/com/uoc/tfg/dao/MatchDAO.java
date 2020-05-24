package com.uoc.tfg.dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.uoc.tfg.model.auxiliar.StatsRating;
import com.uoc.tfg.model.auxiliar.StatsRatingMatch;
import com.uoc.tfg.model.bd.Match;
import com.uoc.tfg.model.bd.StatisticsMatch;

public class MatchDAO extends DAO{

	public MatchDAO() throws Exception {
		super();
	}

	/**
	 * Afegim un partit
	 * 
	 * @param id
	 * @param idAwayTeam
	 * @param idHomeTeam
	 * @param date
	 * @param season
	 * @param location
	 * @throws Exception
	 */
	public void addMatch(int id, int idAwayTeam, int idHomeTeam, Date date, String season, String location) throws Exception {
		PreparedStatement ps = null;
		Connection conn = this.getConn();
		
		try {
			ps = conn.prepareStatement("insert into matchs (id, idawayteam, idhometeam, date, season, location) values (?, ?, ?, ?, ?, ?); ");
			ps.setInt(1, id);
			ps.setInt(2, idAwayTeam);
			ps.setInt(3, idHomeTeam);
			ps.setDate(4, date);
			ps.setString(5, season);
			ps.setString(6, location);
			
			
			ps.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Error a MatchDAO addMatch");
			throw ex;
		}
		finally {
		}
	}
	
	/**
	 * Obtenim la informació d'un partit concret
	 * @param idMatch
	 * @return
	 * @throws Exception
	 */
	public Match getMatch(int idMatch) throws Exception {
		Match m = new Match();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Connection conn = this.getConn();
		
		try {
			ps = conn.prepareStatement("select * from matchs where id = ?");
			ps.setInt(1, idMatch);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				m.setId(idMatch);
				m.setIdAwayTeam(rs.getInt("idawayteam"));
				m.setIdHomeTeam(rs.getInt("idhometeam"));
				m.setDate(rs.getDate("date"));
				m.setSeason(rs.getString("season"));
				m.setLocation(rs.getString("location"));
			} else
				return null;
			
		} catch (Exception ex) {
			System.out.println("Error a MatchDAO getMatch");
			throw ex;
		}
		finally {
		}

		return m;
	}
	
	/**
	 * Obtenim les dades d'un equip contra els equips d'un determinat rang
	 * 
	 * @param idTeam
	 * @param idStatistics
	 * @param location
	 * @param idRankOpp
	 * @return
	 * @throws Exception
	 */
	public StatsRating getTeamMatchStatisticsVsRank(int idTeam, int idStatistics, String location, int idRankOpp) throws Exception{
		DateFormat df = new SimpleDateFormat("MM/dd");
		StatsRating sr = new StatsRating();
		StatsRatingMatch srm = new StatsRatingMatch();
		sr.setLstStats(new ArrayList<StatsRatingMatch>());
		float value = 0;
		float total = 0;
		float units = 0;
		float max = 0;
		float min = 1000;
		float last = 0;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Connection conn = this.getConn();
		try {
			String query = "";
			if("All".equals(location) || "Home".equals(location)) {
				query += "select t.abbreviation, sm.hometeam as teamstat, sm.awayteam as oppstat, 'H' as location, m.date from statistics_match sm \r\n" + 
						"inner join matchs m on m.id = sm.idmatch \r\n" + 
						"inner join team t on m.idawayteam = t.id \r\n" + 
						"inner join team t2 on m.idhometeam = t2.id \r\n" +
						"inner join team_rank tr on tr.idrank = ? and (t.id = tr.idteam)\r\n" + 
						"where (m.idhometeam = ?) and sm.idstatistics = ? \r\n";
			}
			if("All".equals(location)) {
				query += " union \r\n";
			}
			if("All".equals(location) || "Away".equals(location)) {
				query += "select t.abbreviation, sm.awayteam as teamstat, sm.hometeam as oppstat, 'A' as location, m.date from statistics_match sm \r\n" + 
						"inner join matchs m on m.id = sm.idmatch \r\n" + 
						"inner join team t on m.idhometeam = t.id  \r\n" + 
						"inner join team t2 on m.idawayteam = t2.id \r\n" +
						"inner join team_rank tr on tr.idrank = ? and (t.id = tr.idteam)\r\n" + 
						"where (m.idawayteam = ?) and sm.idstatistics = ?";
			}
			query += " order by date desc ";
			
			ps = conn.prepareStatement(query);
			int i = 1;
			if("All".equals(location) || "Home".equals(location)) {
				ps.setInt(i++, idRankOpp);
				ps.setInt(i++, idTeam);
				ps.setInt(i++, idStatistics);
			}
			if("All".equals(location) || "Away".equals(location)) {
				ps.setInt(i++, idRankOpp);
				ps.setInt(i++, idTeam);
				ps.setInt(i++, idStatistics);
			}
			rs = ps.executeQuery();
			while(rs.next()) {
				srm = new StatsRatingMatch();
				units++;
				if("A".equals(rs.getString("location"))) 
					srm.setLocation("F");
				else
					srm.setLocation("C");
				srm.setOpp(rs.getString("abbreviation"));
				srm.setDate(df.format(rs.getDate("date")));
				srm.setTeamStat(rs.getInt("teamstat"));
				srm.setOppStat(rs.getInt("oppstat"));
				srm.setSumStat(srm.getOppStat()+srm.getTeamStat());
				value = rs.getInt("teamstat");
				
				total += value; 
				if(last == 0)
					last = value;
				if(max < value)
					max = value;
				if(min > value)
					min = value;
				
				sr.getLstStats().add(srm);
			}
				
			
		} catch (Exception ex) {
			System.out.println("Error a MatchDAO getTeamMatchStatisticsVsRank");
			throw ex;
		}
		finally {
		}
		if(units == 0)
			sr.setAvg(0);
		else {
			float media = total/units;
			sr.setAvg(round2(media));
		}
		sr.setLast(last);
		sr.setMax(max);
		sr.setMin(min);
		
		return sr;
	}
	
	/**
	 * Afegim les estadístiques d'un partit
	 * 
	 * @param idMatch
	 * @param idStatistics
	 * @param awayTeam
	 * @param homeTeam
	 * @throws Exception
	 */
	public void addStatisticsMatch(int idMatch, int idStatistics, int awayTeam, int homeTeam) throws Exception {
		PreparedStatement ps = null;
		Connection conn = this.getConn();
		
		try {
			ps = conn.prepareStatement("insert into statistics_match (idmatch, idstatistics, awayteam, hometeam) values (?, ?, ?, ?); ");
			ps.setInt(1, idMatch);
			ps.setInt(2, idStatistics);
			ps.setInt(3, awayTeam);
			ps.setInt(4, homeTeam);
			
			ps.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Error a MatchDAO addStatisticsMatch");
			throw ex;
		}
		finally {
		}
	}
	
	/**
	 * Obtenim la llista de partits d'un dia concret
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public List<Match> getDayMatchs(Date date) throws Exception{
		Match m = new Match();
		List<Match> lstMatch = new ArrayList<Match>(); 
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Connection conn = this.getConn();
		
		try {
			ps = conn.prepareStatement("select * from matchs where date = ?");
			ps.setDate(1, date);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				m = new Match();
				m.setId(rs.getInt("id"));
				m.setIdAwayTeam(rs.getInt("idawayteam"));
				m.setIdHomeTeam(rs.getInt("idhometeam"));
				m.setDate(rs.getDate("date"));
				m.setSeason(rs.getString("season"));
				m.setLocation(rs.getString("location"));
				lstMatch.add(m);
			} 
			
		} catch (Exception ex) {
			System.out.println("Error a MatchDAO getDayMatchs");
			throw ex;
		}
		finally {
		}

		return lstMatch;
	}
	
	/**
	 * Obtenim la llista de partits d'un rang de dies
	 * 
	 * @param dateini
	 * @param datefin
	 * @return
	 * @throws Exception
	 */
	public List<Match> getDayMatchs(Date dateini, Date datefin) throws Exception{
		Match m = new Match();
		List<Match> lstMatch = new ArrayList<Match>(); 
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Connection conn = this.getConn();
		
		try {
			ps = conn.prepareStatement("select * from matchs where date >= ? and date <= ?");
			ps.setDate(1, dateini);
			ps.setDate(2, datefin);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				m = new Match();
				m.setId(rs.getInt("id"));
				m.setIdAwayTeam(rs.getInt("idawayteam"));
				m.setIdHomeTeam(rs.getInt("idhometeam"));
				m.setDate(rs.getDate("date"));
				m.setSeason(rs.getString("season"));
				m.setLocation(rs.getString("location"));
				lstMatch.add(m);
			} 
			
		} catch (Exception ex) {
			System.out.println("Error a MatchDAO getDayMatchs");
			throw ex;
		}
		finally {
		}

		return lstMatch;
	}
	
	/**
	 * Obtenim les dades d'un equip o el seu oponent d'una estadística, una data inicial, una localització i un nombre de dies concrets
	 * 
	 * @param idTeam
	 * @param idStatistics
	 * @param quantity
	 * @param d
	 * @param location
	 * @param opponent
	 * @return
	 * @throws Exception
	 */
	public float getTeamMatchStatistics(int idTeam, int idStatistics, int quantity, Date d, String location, boolean opponent) throws Exception{
		float total = 0;
		float units = 0;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Connection conn = this.getConn();
		try {
			String query = "";
			if("All".equals(location) || "Home".equals(location)) {
				query += "select sm.hometeam as team, sm.awayteam as opp, m.date \r\n" + 
						"from statistics_match sm \r\n" + 
						"inner join matchs m on sm.idmatch = m.id \r\n" + 
						"where m.idhometeam = ? \r\n" + 
						"and m.date >= ? \r\n" + 
						"and sm.idstatistics = ?";
			}
			if("All".equals(location)) {
				query += " union ";
			}
			if("All".equals(location) || "Away".equals(location)) {
				query += "select sm.awayteam as team, sm.hometeam as opp, m.date \r\n" + 
						"from statistics_match sm \r\n" + 
						"inner join matchs m on sm.idmatch = m.id \r\n" + 
						"where m.idawayteam = ? \r\n" + 
						" and m.date >= ? \r\n" + 
						"and sm.idstatistics = ?";
			}
			ps = conn.prepareStatement(query);
			int i = 1;
			if("All".equals(location) || "Home".equals(location)) {
				ps.setInt(i++, idTeam);
				ps.setDate(i++, d);
				ps.setInt(i++, idStatistics);
			}
			if("All".equals(location) || "Away".equals(location)) {
				ps.setInt(i++, idTeam);
				ps.setDate(i++, d);
				ps.setInt(i++, idStatistics);
			}
			rs = ps.executeQuery();
			while(rs.next()) {
				units++;
				if(opponent)
					total+= rs.getInt("opp");
				else
					total+= rs.getInt("team");
				quantity--;
				if(quantity == 0)
					break;
			}
				
			
		} catch (Exception ex) {
			System.out.println("Error a MatchDAO getTeamMatchStatistics");
			throw ex;
		}
		finally {
		}
		if(units == 0)
			return 0;
		float media = total/units;
		return round2(media);
	}
	
	public static float round2(float number) {
	    int pow = 100;
	    float tmp = number * pow;
	    return ( (float) ( (int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp) ) ) / pow;
	}
	
	/**
	 * Obtenim totes les estadístiques d'un partit
	 * 
	 * @param idMatch
	 * @return
	 * @throws Exception
	 */
	public List<StatisticsMatch> getStatisticsMatch(int idMatch) throws Exception {
		StatisticsMatch st = new StatisticsMatch();
		List<StatisticsMatch> lstSt = new ArrayList<StatisticsMatch>(); 
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Connection conn = this.getConn();
		
		try {
			ps = conn.prepareStatement("select * from statistics_match where idmatch = ?");
			ps.setInt(1, idMatch);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				st = new StatisticsMatch();
				st.setId(rs.getInt("id"));
				st.setIdMatch(rs.getInt("idmatch"));
				st.setIdStatistics(rs.getInt("idstatistics"));
				st.setAwayTeam(rs.getInt("awayteam"));
				st.setHomeTeam(rs.getInt("hometeam"));
				
				lstSt.add(st);
			} 
			
		} catch (Exception ex) {
			System.out.println("Error a MatchDAO getStatisticsMatch");
			throw ex;
		}
		finally {
		}

		return lstSt;
	}
	
	/**
	 * Obtenim el llistat dels darrers n partits d'un equip
	 * 
	 * @param idTeam
	 * @param num
	 * @return
	 * @throws Exception
	 */
	public List<Match> getLastGames(int idTeam, int num) throws Exception {
		Match m = new Match();
		List<Match> lstMatch = new ArrayList<>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Connection conn = this.getConn();
		
		try {
			ps = conn.prepareStatement("select * from matchs m "
					+ " 	inner join statistics_match sm on sm.idmatch = m.id and idstatistics = 6"
					+ " where m.idawayteam = ? or m.idhometeam = ?"
					+ " order by m.date desc");
			ps.setInt(1, idTeam);
			ps.setInt(2, idTeam);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				m = new Match();
				m.setId(rs.getInt("id"));
				m.setIdAwayTeam(rs.getInt("idawayteam"));
				m.setIdHomeTeam(rs.getInt("idhometeam"));
				m.setDate(rs.getDate("date"));
				m.setSeason(rs.getString("season"));
				m.setLocation(rs.getString("location"));
				lstMatch.add(m);
				num--;
				if(num == 0)
					break;
			}
		} catch (Exception ex) {
			System.out.println("Error a MatchDAO getLastGames");
			throw ex;
		}
		
		return lstMatch;
	}
	
	/**
	 * Obtenim el llistat dels propers n partits d'un equip
	 * 
	 * @param idTeam
	 * @param num
	 * @return
	 * @throws Exception
	 */
	public List<Match> getNextGames(int idTeam, int num) throws Exception {
		Match m = new Match();
		List<Match> lstMatch = new ArrayList<>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Connection conn = this.getConn();
		
		try {
			ps = conn.prepareStatement("select * from matchs m"
					+ " where (m.idawayteam = ? or m.idhometeam = ?)  "
					+ " 	and id not in (select idMatch from statistics_match)"
					+ "order by m.date asc");
			ps.setInt(1, idTeam);
			ps.setInt(2, idTeam);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				m = new Match();
				m.setId(rs.getInt("id"));
				m.setIdAwayTeam(rs.getInt("idawayteam"));
				m.setIdHomeTeam(rs.getInt("idhometeam"));
				m.setDate(rs.getDate("date"));
				m.setSeason(rs.getString("season"));
				m.setLocation(rs.getString("location"));
				lstMatch.add(m);
				num--;
				if(num == 0)
					break;
			}
		} catch (Exception ex) {
			System.out.println("Error a MatchDAO getNextGames");
			throw ex;
		}
		
		return lstMatch;
	}
	
	/**
	 * Obtenim la data del darrer partit del qual tenim estadístiques
	 * 
	 * @return
	 * @throws Exception
	 */
	public Date getMaxDate() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Connection conn = this.getConn();
		try {
			ps = conn.prepareStatement("select max(date) from matchs m inner join statistics_match sm on m.id = sm.idmatch");
			rs = ps.executeQuery();
			if(rs.next())
				return rs.getDate(1);
		} catch (Exception ex) {
			System.out.println("Error a MatchDAO getMaxDate");
			throw ex;
		}
		return null;
		
	}
	
	/**
	 * Obtenim la data del primer partit del qual tenim informació
	 * 
	 * @return
	 * @throws Exception
	 */
	public Date getMinDate() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Connection conn = this.getConn();
		try {
			ps = conn.prepareStatement("select min(date) from matchs");
			rs = ps.executeQuery();
			if(rs.next())
				return rs.getDate(1);
		} catch (Exception ex) {
			System.out.println("Error a MatchDAO getMinDate");
			throw ex;
		}
		return null;
		
	}
}
