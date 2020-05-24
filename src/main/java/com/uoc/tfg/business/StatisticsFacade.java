package com.uoc.tfg.business;

import java.util.List;

import com.uoc.tfg.model.auxiliar.RatingList;
import com.uoc.tfg.model.auxiliar.StatisticsList;
import com.uoc.tfg.model.bd.Rank;
import com.uoc.tfg.model.bd.Rating;
import com.uoc.tfg.model.bd.Statistics;

public interface StatisticsFacade {

	public void addStatistics(String type, String description) throws Exception;
	public Statistics getStatistics(int idStatistics) throws Exception;
	public void updateStatistics(int idStatistics, String description) throws Exception;
	public void activeStatistics(int idStatistics, boolean active) throws Exception;
	public void addRating(int idStatistics, String name, String description, int days, int numberMatchs, String location) throws Exception;
	public Rating getRating(int idRating) throws Exception;
	public void updateRating(int idRating, int idStatistics, String name, String description, int days, int numberMatchs, String location) throws Exception;
	public void activeRating(int idRating, boolean active) throws Exception;
	public void addRank(int idRating, String name, String description, float min, float max) throws Exception;
	public Rank getRank(int idRank) throws Exception;
	public void updateRank(int idRank, int idRating, String name, String description, float min, float max) throws Exception;
	public void deleteRank(int idRank) throws Exception;
	public List<RatingList> getAllRatings() throws Exception;
	public List<StatisticsList> getAllStatistics() throws Exception;
	public List<Rank> getAllRanks(int idRating) throws Exception;
	public void createInitialStatistics() throws Exception;
	public void createRanks(int idRating) throws Exception;
}
