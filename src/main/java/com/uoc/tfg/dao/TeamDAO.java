package com.uoc.tfg.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.uoc.tfg.model.auxiliar.Standing;
import com.uoc.tfg.model.bd.Rank;
import com.uoc.tfg.model.bd.Team;

public class TeamDAO extends DAO {

	public TeamDAO() throws Exception {
		super();
	}
	
	/**
	 * Afegim un equip
	 * 
	 * @param id
	 * @param name
	 * @param city
	 * @param abbreviation
	 * @param conference
	 * @throws Exception 
	 */
	public void addTeam(int id, String name, String city, String abbreviation, String conference) throws Exception {
		PreparedStatement ps = null;
		Connection conn = this.getConn();
		
		try {
			ps = conn.prepareStatement("insert into team (id, name, city, abbreviation, conference) values (?, ?, ?, ?, ?); ");
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, city);
			ps.setString(4, abbreviation);
			ps.setString(5, conference);
			
			ps.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Error a TeamDAO addTeam");
			throw ex;
		}
		finally {
		}
	}
	
	/**
	 * Obtenir la informació bàsica d'un equip
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Team getTeam(int id) throws Exception {
		Team team = new Team();
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		Connection conn = this.getConn();
		
		try {
			ps = conn.prepareStatement("select *  from team where id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				team.setId(id);
				team.setName(rs.getString("name"));
				team.setCity(rs.getString("city"));
				team.setAbbreviation(rs.getString("abbreviation"));
				team.setConference(rs.getString("conference"));
				team.setWins(rs.getInt("wins"));
				team.setLoses(rs.getInt("loses"));
			} else 
				return null;
			
		} catch (Exception ex) {
			System.out.println("Error a TeamDAO getTeam");
			throw ex;
		}
		finally {
		}
		
		
		return team;
	}
	
	/**
	 * Assignem a un equip un rang concret
	 * 
	 * @param idTeam
	 * @param idRank
	 */
	public void addTeamRank(int idTeam, int idRank) throws Exception {
		PreparedStatement ps = null;
		Connection conn = this.getConn();
		
		try {
			ps = conn.prepareStatement("insert into team_rank (idteam, idrank) values (?, ?); ");
			ps.setInt(1, idTeam);
			ps.setInt(2, idRank);
			
			ps.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Error a TeamDAO addTeamRank");
			throw ex;
		}
		finally {
		}
	}
	
	/**
	 * Eliminem la relació d'un equip amb un rang
	 * 
	 * @param idTeam
	 * @param idRank
	 */
	public void cleanTeamsRanks(int idRating) throws Exception {
		PreparedStatement ps = null;
		Connection conn = this.getConn();
		
		try {
			ps = conn.prepareStatement("delete from team_rank where idrank in (select id from ranks where idrating = ?)");
			ps.setInt(1, idRating);
			
			ps.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Error a TeamDAO cleanTeamsRanks");
			throw ex;
		}
		finally {
		}
	}
	
	/**
	 * Obtenim la informació del rang concret d'un equip en un rànquing concret
	 * 
	 * @param idTeamRank
	 * @return
	 */
	public Rank getTeamRank(int idTeam, int idRating) throws Exception {
		Rank rank = new Rank();
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		Connection conn = this.getConn();
		
		try {
			ps = conn.prepareStatement("select rk.* from ranks rk "
					+ "inner join rating rt on rt.id = rk.idRating "
					+ "inner join team_rank tr on tr.idrank = rk.id "
					+ "where tr.idteam = ? and rt.id = ?");
			ps.setInt(1, idTeam);
			ps.setInt(2, idRating);
			rs = ps.executeQuery();
			if(rs.next()) {
				rank.setId(rs.getInt("id"));
				rank.setIdRating(rs.getInt("idRating"));
				rank.setName(rs.getString("name"));
				rank.setDescription(rs.getString("description"));
				rank.setMin(rs.getFloat("min"));
				rank.setMax(rs.getFloat("max"));
			} else
				return null;
		} catch (Exception ex) {
			System.out.println("Error a TeamDAO getTeamRank");
			throw ex;
		}
		
		return rank;
	}
	
	/**
	 * Obtenim la informació bàsica de tots els equips
	 * 
	 * @return
	 */
	public List<Team> getAllTeams() throws Exception {
		List<Team> listT = new ArrayList<Team>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Connection conn = this.getConn();
		
		try {
			ps = conn.prepareStatement("select * from team");
			
			rs = ps.executeQuery();
			while(rs.next()) {
				Team t = new Team();
				t.setId(rs.getInt("id"));
				t.setName(rs.getString("name"));
				t.setCity(rs.getString("city"));
				t.setAbbreviation(rs.getString("abbreviation"));
				t.setConference(rs.getString("conference"));
				t.setWins(rs.getInt("wins"));
				t.setLoses(rs.getInt("loses"));
				listT.add(t);
			}
		} catch (Exception ex) {
			System.out.println("Error a TeamDAO getAllTeams");
			throw ex;
		}
		finally {
		}
		
		return listT;
	}
	
	/**
	 * Actualitzem la informació de victòries i derrotes d'un equip
	 * 
	 * @param id
	 * @param wins
	 * @param loses
	 * @throws Exception 
	 */
	public void updateTeam(int id, int wins, int loses) throws Exception {
		PreparedStatement ps = null;
		Connection conn = this.getConn();
		
		try {
			ps = conn.prepareStatement("update team set wins = ?, loses = ? where id = ?");
			ps.setInt(1, wins);
			ps.setInt(2, loses);
			ps.setInt(3, id);
			
			ps.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Error a TeamDAO updateTeam");
			throw ex;
		}
		finally {
		}
	}
	
	/**
	 * Retorna la classificació de la conferencia d'entrada
	 * 
	 * @param conference
	 * @return
	 * @throws Exception 
	 */
	public List<Standing> getStanding(String conference) throws Exception {
		PreparedStatement ps = null;
		Connection conn = this.getConn();
		List<Standing> lstStanding = new ArrayList<Standing>();
		Standing standing = new Standing();
		ResultSet rs = null;
		String conferenceName = "";
		switch(conference) {
		case "E":
			conferenceName = "Est";
			break;
		case "W":
			conferenceName = "Oest";
			break;
		default:
			conferenceName = conference;	
		}
		try {
			ps = conn.prepareStatement("select * from team where conference = ? order by wins desc, loses asc");
			ps.setString(1, conference);
			
			rs = ps.executeQuery();
			int pos = 1;
			while(rs.next()) {
				standing = new Standing();
				standing.setPosition(pos);
				standing.setConference(conferenceName);
				standing.setIdTeam(rs.getInt("id"));
				standing.setTeamName(rs.getString("city")+" "+rs.getString("name"));
				standing.setWins(rs.getInt("wins"));
				standing.setLoses(rs.getInt("loses"));
				lstStanding.add(standing);
				pos++;
			}
		} catch (Exception ex) {
			System.out.println("Error a TeamDAO getStanding");
			throw ex;
		}
					
		return lstStanding;
	}
}
