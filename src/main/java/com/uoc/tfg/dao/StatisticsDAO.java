package com.uoc.tfg.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.uoc.tfg.model.bd.Rank;
import com.uoc.tfg.model.bd.Rating;
import com.uoc.tfg.model.bd.Statistics;

public class StatisticsDAO extends DAO {

	public StatisticsDAO() throws Exception {
		super();
	}

	/**
	 * Afegir un tipus d'estadística
	 * 
	 * @param type
	 * @param description
	 */
	public void addStatistics(String type, String description) throws Exception {
		PreparedStatement ps = null;
		Connection conn = this.getConn();
		
		try {
			ps = conn.prepareStatement("insert into statistics (type, description, active) values (?, ?, 1); ");
			ps.setString(1, type);
			ps.setString(2, description);
			
			ps.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Error a StatisticsDAO addStatistics");
			throw ex;
		}
	}
	
	/**
	 * Obtenim la informació bàsica d'una estadística
	 * 
	 * @param idStatistics
	 * @return
	 */
	public Statistics getStatistics(int idStatistics) throws Exception {
		Statistics st = new Statistics();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Connection conn = this.getConn();
		
		try {
			ps = conn.prepareStatement("select * from statistics where id = ? ");
			ps.setInt(1, idStatistics);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				st.setId(idStatistics);
				st.setType(rs.getString("type"));
				st.setDescription(rs.getString("description"));
				st.setActive(rs.getBoolean("active"));
			}
		} catch (Exception ex) {
			System.out.println("Error a StatisticsDAO getStatistics");
			throw ex;
		}
		
		return st;
	}

	/**
	 * Actualitzem la informació d'una estadística
	 * 
	 * @param idStatistics
	 * @param type
	 * @param description
	 */
	public void updateStatistics(int idStatistics, String description) throws Exception {
		PreparedStatement ps = null;
		Connection conn = this.getConn();
		
		try {
			ps = conn.prepareStatement("update statistics set description = ? where id = ?");
			ps.setString(1, description);
			ps.setInt(2, idStatistics);
			
			ps.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Error a StatisticsDAO updateStatistics");
			throw ex;
		}
		
	}
	/**
	 * Activem o desactivem una estadística concreta
	 * 
	 * @param idStatistics
	 * @param active
	 */
	public void activeStatistics(int idStatistics, boolean active) throws Exception {
		PreparedStatement ps = null;
		Connection conn = this.getConn();
		
		try {
			ps = conn.prepareStatement("update statistics set active = ? where id = ?");
			ps.setBoolean(1, active);
			ps.setInt(2, idStatistics);
			
			ps.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Error a StatisticsDAO activeStatistics");
			throw ex;
		}
		
	}
	
	/**
	 * Afegim un nou rànquing
	 * 
	 * @param idStatistics
	 * @param name
	 * @param description
	 * @param type
	 * @throws Exception 
	 */
	public void addRating(int idStatistics, String name, String description, int days, int numberMatchs, String location) throws Exception {
		PreparedStatement ps = null;
		Connection conn = this.getConn();
		
		try {
			String sql = "insert into rating (idstatistics, name, description, ";
			String values = "values (?,?,?";
			if(days > 0) {
				sql += "days, ";
				values += ",?";
			}
			if(numberMatchs > 0) {
				sql += "numbermatchs, ";
				values += ",?";
			}
			if(location != null) {
				sql += "location, ";
				values += ",?";
			}
			sql += "active) ";
			values += ",1)";
			ps = conn.prepareStatement(sql+values);
			int i = 1;
			ps.setInt(i++, idStatistics);
			ps.setString(i++, name);
			ps.setString(i++, description);
			if(days > 0)
				ps.setInt(i++, days);
			if(numberMatchs > 0)
				ps.setInt(i++, numberMatchs);
			if(location != null)
				ps.setString(i++, location);
			
			ps.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Error a StatisticsDAO addRating");
			throw ex;
		}
		
	}
	
	/**
	 * Obtenim la informació d'un rànquing
	 * 
	 * @param idRating
	 * @return
	 */
	public Rating getRating(int idRating) throws Exception {
		Rating rat = new Rating();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Connection conn = this.getConn();
		
		try {
			ps = conn.prepareStatement("select * from rating where id = ? ");
			ps.setInt(1, idRating);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				rat.setId(idRating);
				rat.setIdStatistics(rs.getInt("idstatistics"));
				rat.setName(rs.getString("name"));
				rat.setDescription(rs.getString("description"));
				rat.setDays(rs.getInt("days"));
				rat.setNumberMatchs(rs.getInt("numbermatchs"));
				rat.setLocation(rs.getString("location"));
				rat.setActive(rs.getBoolean("active"));
				rat.setAutomatic(rs.getBoolean("automatic"));
			}
		} catch (Exception ex) {
			System.out.println("Error a StatisticsDAO getRating");
			throw ex;
		}
		
		return rat;
	}

	/**
	 * Actualitzem la informació d'un rànquing
	 * 
	 * @param idRating
	 * @param idStatistics
	 * @param name
	 * @param description
	 * @param type
	 */
	public void updateRating(int idRating, int idStatistics, String name, String description, int days, int numberMatchs, String location) throws Exception {
		PreparedStatement ps = null;
		Connection conn = this.getConn();
		
		try {
			String sql = "update rating set idstatistics = ?, name = ?, description = ?";
			if(days > 0) 
				sql += ", days = ?";
			if(numberMatchs > 0) 
				sql += ", numbermatchs = ?";
			if(location != null) 
				sql += ", location = ?";
			sql += " where id = ?";
			ps = conn.prepareStatement(sql);
			int i = 1;
			ps.setInt(i++, idStatistics);
			ps.setString(i++, name);
			ps.setString(i++, description);
			if(days > 0) 
				ps.setInt(i++, days);
			if(numberMatchs > 0) 
				ps.setInt(i++, numberMatchs);
			if(location != null) 
				ps.setString(i++, location);
			ps.setInt(i++, idRating);
			
			ps.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Error a StatisticsDAO updateRating");
			throw ex;
		}
		
	}
	
	/**
	 * Activem o desactivem un rànquing 
	 * 
	 * @param idRating
	 * @param active
	 */
	public void activeRating(int idRating, boolean active) throws Exception {
		PreparedStatement ps = null;
		Connection conn = this.getConn();
		
		try {
			ps = conn.prepareStatement("update rating set active = ? where id = ?");
			ps.setBoolean(1, active);
			ps.setInt(2, idRating);
			
			ps.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Error a StatisticsDAO activeRating");
			throw ex;
		}
		
	}
	
	/**
	 * Activem o desactivem un rànquing 
	 * 
	 * @param idRating
	 * @param active
	 */
	public void autoRating(int idRating, boolean active) throws Exception {
		PreparedStatement ps = null;
		Connection conn = this.getConn();
		
		try {
			ps = conn.prepareStatement("update rating set automatic = ? where id = ?");
			ps.setBoolean(1, active);
			ps.setInt(2, idRating);
			
			ps.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Error a StatisticsDAO autoRating");
			throw ex;
		}
		
	}
	
	/**
	 * Afegim un rang
	 * 
	 * @param idRating
	 * @param name
	 * @param description
	 * @param min
	 * @param max
	 * @throws Exception 
	 */
	public void addRank(int idRating, String name, String description, float min, float max) throws Exception {
		PreparedStatement ps = null;
		Connection conn = this.getConn();
		
		try {
			ps = conn.prepareStatement("insert into ranks (name, description, min, max, idRating) values (?, ?, ?, ?, ?); ");
			ps.setString(1, name);
			ps.setString(2, description);
			ps.setFloat(3, min);
			ps.setFloat(4, max);
			ps.setInt(5, idRating);
			
			ps.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Error a StatisticsDAO addRank");
			throw ex;
		}
		
	}
	
	/**
	 * Obtenim la informació d'un rang
	 * 
	 * @param idRank
	 * @return
	 * @throws Exception 
	 */
	public Rank getRank(int idRank) throws Exception {
		Rank r = new Rank();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Connection conn = this.getConn();
		
		try {
			ps = conn.prepareStatement("select * from ranks where id = ? ");
			ps.setInt(1, idRank);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				r.setId(idRank);
				r.setIdRating(rs.getInt("idRating"));
				r.setName(rs.getString("name"));
				r.setDescription(rs.getString("description"));
				r.setMin(rs.getFloat("min"));
				r.setMax(rs.getFloat("max"));
			}
		} catch (Exception ex) {
			System.out.println("Error a StatisticsDAO getRank");
			throw ex;
		}
		
		return r;
	}
	
	/**
	 * Actualitzem la informació d'un rang
	 * 
	 * @param idRank
	 * @param idRating
	 * @param name
	 * @param description
	 * @param min
	 * @param max
	 * @throws Exception 
	 */
	public void updateRank(int idRank, int idRating, String name, String description, float min, float max) throws Exception {
		PreparedStatement ps = null;
		Connection conn = this.getConn();
		
		try {
			ps = conn.prepareStatement("update ranks set name = ?, description = ?, min = ?, max = ? where id = ?");
			ps.setString(1, name);
			ps.setString(2, description);
			ps.setFloat(3, min);
			ps.setFloat(4, max);
			ps.setInt(5, idRank);
			
			ps.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Error a StatisticsDAO updateRank");
			throw ex;
		}
	}
	
	/**
	 * Eliminem un rang
	 * 
	 * @param idRank
	 * @throws Exception 
	 */
	public void deleteRank(int idRank) throws Exception {
		PreparedStatement ps = null;
		Connection conn = this.getConn();
		
		try {
			ps = conn.prepareStatement("delete from team_rank where idrank = ?");
			ps.setInt(1, idRank);
			
			ps.executeUpdate();
			
			ps = conn.prepareStatement("delete from ranks where id = ?");
			ps.setInt(1, idRank);
			
			ps.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Error a StatisticsDAO deleteRank");
			throw ex;
		}
		
	}
	
	/**
	 * Obtenim un llistat amb totes les estadístiques
	 * 
	 * @return
	 */
	public List<Statistics> getAllStatistics() throws Exception {
		
		List<Statistics> listSt = new ArrayList<Statistics>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Connection conn = this.getConn();
		
		try {
			ps = conn.prepareStatement("select * from statistics");
			
			rs = ps.executeQuery();
			while(rs.next()) {
				Statistics st = new Statistics();
				st.setId(rs.getInt("id"));
				st.setType(rs.getString("type"));
				st.setDescription(rs.getString("description"));
				st.setActive(rs.getBoolean("active"));
				listSt.add(st);
			}
		} catch (Exception ex) {
			System.out.println("Error a StatisticsDAO getAllStatistics");
			throw ex;
		}
		
		return listSt;
	}
	
	/**
	 * Obtenim un llistat amb tots els rànquings
	 * 
	 * @return
	 * @throws Exception 
	 */
	public List<Rating> getAllRatings() throws Exception{

		List<Rating> listRat = new ArrayList<Rating>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Connection conn = this.getConn();
		
		try {
			ps = conn.prepareStatement("select * from rating");
			
			rs = ps.executeQuery();
			while(rs.next()) {
				Rating rat = new Rating();
				rat.setId(rs.getInt("id"));
				rat.setIdStatistics(rs.getInt("idstatistics"));
				rat.setName(rs.getString("name"));
				rat.setDescription(rs.getString("description"));
				rat.setDays(rs.getInt("days"));
				rat.setNumberMatchs(rs.getInt("numbermatchs"));
				rat.setLocation(rs.getString("location"));
				rat.setActive(rs.getBoolean("active"));
				rat.setAutomatic(rs.getBoolean("automatic"));
				listRat.add(rat);
			}
		} catch (Exception ex) {
			System.out.println("Error a StatisticsDAO getAllRatings");
			throw ex;
		}
		
		return listRat;
	}
	
	/**
	 * Obtenim tots els rangs d'un rànquing
	 * 
	 * @param idRating
	 * @return
	 */
	public List<Rank> getRanksRating(int idRating) throws Exception{

		List<Rank> lstRank = new ArrayList<Rank>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Connection conn = this.getConn();
		
		try {
			ps = conn.prepareStatement("select * from ranks where idRating = ? order by min asc");
			ps.setInt(1, idRating);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				Rank rank = new Rank();
				rank.setId(rs.getInt("id"));
				rank.setIdRating(rs.getInt("idRating"));
				rank.setName(rs.getString("name"));
				rank.setDescription(rs.getString("description"));
				rank.setMin(rs.getFloat("min"));
				rank.setMax(rs.getFloat("max"));
				lstRank.add(rank);
			}
		} catch (Exception ex) {
			System.out.println("Error a StatisticsDAO getRanksRating");
			throw ex;
		}
		
		return lstRank;
	}
	
	/**
	 * Obtenim tots els rànquings d'una estadística
	 * 
	 * @param idStatistics
	 * @return
	 * @throws Exception
	 */
	public List<Rating> getRatingStatistics(int idStatistics) throws Exception{
		List<Rating> listRat = new ArrayList<Rating>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Connection conn = this.getConn();
		
		try {
			ps = conn.prepareStatement("select * from rating where idstatistics = ? and active = 1");
			ps.setInt(1, idStatistics);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				Rating rat = new Rating();
				rat.setId(rs.getInt("id"));
				rat.setIdStatistics(rs.getInt("idstatistics"));
				rat.setName(rs.getString("name"));
				rat.setDescription(rs.getString("description"));
				rat.setDays(rs.getInt("days"));
				rat.setNumberMatchs(rs.getInt("numbermatchs"));
				rat.setLocation(rs.getString("location"));
				rat.setActive(rs.getBoolean("active"));
				listRat.add(rat);
			}
		} catch (Exception ex) {
			System.out.println("Error a StatisticsDAO getRatingStatistics");
			throw ex;
		}
		
		return listRat;
	}
}
