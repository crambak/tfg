package com.uoc.tfg.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.uoc.tfg.business.MatchFacadeBean;
import com.uoc.tfg.business.StatisticsFacadeBean;
import com.uoc.tfg.model.auxiliar.MatchTeam;
import com.uoc.tfg.model.auxiliar.StatisticsList;
import com.uoc.tfg.model.auxiliar.Stats;
import com.uoc.tfg.model.auxiliar.StatsRating;
import com.uoc.tfg.model.auxiliar.StatsRatingMatch;
import com.uoc.tfg.model.bd.Match;
import com.uoc.tfg.model.bd.Rating;
import com.uoc.tfg.model.bd.Statistics;

@ManagedBean(name="analysis")
@RequestScoped
public class ShowAnalysisMatch {
	private int idMatch;
	private String date;
	private String location;
	private MatchTeam mtHome;
	private MatchTeam mtAway;
	private String disablePts;
	private String disable1Q;
	private String disable2Q;
	private String disable3Q;
	private String disable4Q;
	private String disableReb;
	private String disableOrb;
	private String disableDrb;
	private int idPts;
	private int id1Q;
	private int id2Q;
	private int id3Q;
	private int id4Q;
	private int idReb;
	private int idOrb;
	private int idDrb;
	private List<Stats> lstStats;
	private String statistic;
	private String disabled;
	private List<SelectItem> lstRating;
	private String disabledListRating;
	private int idRating;
	private int idStatistics;
	private Rating rating;
	private String disabledRating;
	private List<StatsRating> lstStatsRating;
	private float stakeHome;
	private float stakeAway;
	private float stakeTotal;
	
	public int getIdMatch() {
		return idMatch;
	}
	public void setIdMatch(int idMatch) {
		this.idMatch = idMatch;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public MatchTeam getMtHome() {
		return mtHome;
	}
	public void setMtHome(MatchTeam mtHome) {
		this.mtHome = mtHome;
	}
	public MatchTeam getMtAway() {
		return mtAway;
	}
	public void setMtAway(MatchTeam mtAway) {
		this.mtAway = mtAway;
	}
	public String getDisablePts() {
		return disablePts;
	}
	public void setDisablePts(String disablePts) {
		this.disablePts = disablePts;
	}
	public String getDisable1Q() {
		return disable1Q;
	}
	public void setDisable1Q(String disable1q) {
		disable1Q = disable1q;
	}
	public String getDisable2Q() {
		return disable2Q;
	}
	public void setDisable2Q(String disable2q) {
		disable2Q = disable2q;
	}
	public String getDisable3Q() {
		return disable3Q;
	}
	public void setDisable3Q(String disable3q) {
		disable3Q = disable3q;
	}
	public String getDisable4Q() {
		return disable4Q;
	}
	public void setDisable4Q(String disable4q) {
		disable4Q = disable4q;
	}
	public String getDisableReb() {
		return disableReb;
	}
	public void setDisableReb(String disableReb) {
		this.disableReb = disableReb;
	}
	public String getDisableOrb() {
		return disableOrb;
	}
	public void setDisableOrb(String disableOrb) {
		this.disableOrb = disableOrb;
	}
	public String getDisableDrb() {
		return disableDrb;
	}
	public void setDisableDrb(String disableDrb) {
		this.disableDrb = disableDrb;
	}
	public int getIdPts() {
		return idPts;
	}
	public void setIdPts(int idPts) {
		this.idPts = idPts;
	}
	public int getId1Q() {
		return id1Q;
	}
	public void setId1Q(int id1q) {
		id1Q = id1q;
	}
	public int getId2Q() {
		return id2Q;
	}
	public void setId2Q(int id2q) {
		id2Q = id2q;
	}
	public int getId3Q() {
		return id3Q;
	}
	public void setId3Q(int id3q) {
		id3Q = id3q;
	}
	public int getId4Q() {
		return id4Q;
	}
	public void setId4Q(int id4q) {
		id4Q = id4q;
	}
	public int getIdReb() {
		return idReb;
	}
	public void setIdReb(int idReb) {
		this.idReb = idReb;
	}
	public int getIdOrb() {
		return idOrb;
	}
	public void setIdOrb(int idOrb) {
		this.idOrb = idOrb;
	}
	public int getIdDrb() {
		return idDrb;
	}
	public void setIdDrb(int idDrb) {
		this.idDrb = idDrb;
	}
	public List<Stats> getLstStats() {
		return lstStats;
	}
	public void setLstStats(List<Stats> lstStats) {
		this.lstStats = lstStats;
	}
	public String getStatistic() {
		return statistic;
	}
	public void setStatistic(String statistic) {
		this.statistic = statistic;
	}
	public String getDisabled() {
		return disabled;
	}
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
	public List<SelectItem> getLstRating() {
		return lstRating;
	}
	public void setLstRating(List<SelectItem> lstRating) {
		this.lstRating = lstRating;
	}
	public String getDisabledListRating() {
		return disabledListRating;
	}
	public void setDisabledListRating(String disabledListRating) {
		this.disabledListRating = disabledListRating;
	}
	public int getIdRating() {
		return idRating;
	}
	public void setIdRating(int idRating) {
		this.idRating = idRating;
	}
	public int getIdStatistics() {
		return idStatistics;
	}
	public void setIdStatistics(int idStatistics) {
		this.idStatistics = idStatistics;
	}
	public Rating getRating() {
		return rating;
	}
	public void setRating(Rating rating) {
		this.rating = rating;
	}
	public String getDisabledRating() {
		return disabledRating;
	}
	public void setDisabledRating(String disabledRating) {
		this.disabledRating = disabledRating;
	}
	public List<StatsRating> getLstStatsRating() {
		return lstStatsRating;
	}
	public void setLstStatsRating(List<StatsRating> lstStatsRating) {
		this.lstStatsRating = lstStatsRating;
	}
	public float getStakeHome() {
		return stakeHome;
	}
	public void setStakeHome(float stakeHome) {
		this.stakeHome = stakeHome;
	}
	public float getStakeAway() {
		return stakeAway;
	}
	public void setStakeAway(float stakeAway) {
		this.stakeAway = stakeAway;
	}
	public float getStakeTotal() {
		return stakeTotal;
	}
	public void setStakeTotal(float stakeTotal) {
		this.stakeTotal = stakeTotal;
	}
	public ShowAnalysisMatch() throws Exception {
		
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		List<StatisticsList> lstSt = new ArrayList<StatisticsList>();
		StatisticsList st = new StatisticsList();
		if(params.get("idMatch") != null) {
			this.idMatch = Integer.parseInt(params.get("idMatch"));
			MatchFacadeBean mf = new MatchFacadeBean();
			Match m = mf.getMatch(idMatch);
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			date = df.format(m.getDate());
			location = m.getLocation();
			mtHome = mf.getMatchTeam(m.getIdHomeTeam());
			mtAway = mf.getMatchTeam(m.getIdAwayTeam());
	
			StatisticsFacadeBean sf = new StatisticsFacadeBean();
			lstSt = sf.getAllStatistics();
			for(int i = 0; i < lstSt.size(); i++) {
				st = lstSt.get(i);
				switch (st.getType()) {
				case "PTS": 
					idPts = st.getId();
					if(!st.isActiveBol())
						disablePts = "display:none;";
					break;
				case "1QP":
					id1Q = st.getId();
					if(!st.isActiveBol())
						disable1Q = "display:none;";
					break;
				case "2QP":
					id2Q = st.getId();
					if(!st.isActiveBol())
						disable2Q = "display:none;";
					break;
				case "3QP":
					id3Q = st.getId();
					if(!st.isActiveBol())
						disable3Q = "display:none;";
					break;
				case "4QP":
					id4Q = st.getId();
					if(!st.isActiveBol())
						disable4Q = "display:none;";
					break;
				case "REB":
					idReb = st.getId();
					if(!st.isActiveBol())
						disableReb = "display:none;";
					break;
				case "OREB":
					idOrb = st.getId();
					if(!st.isActiveBol())
						disableOrb = "display:none;";
					break;
				case "DREB":
					idDrb = st.getId();
					if(!st.isActiveBol())
						disableDrb = "display:none;";
					break;
				}
			}
			disabledRating = "display:none;";
			if(params.get("idStats") != null) {
				Statistics s = sf.getStatistics(Integer.parseInt(params.get("idStats")));
				idStatistics = s.getId();
				statistic = s.getType()+" - "+s.getDescription();
				lstStats = mf.getStats(idMatch, Integer.parseInt(params.get("idStats")));
				List<Rating> lstRat = sf.getRatingsStats(Integer.parseInt(params.get("idStats")));
				lstRating = new ArrayList<SelectItem>();
				if(lstRat.size()==0) {
					disabledListRating = "display:none;";
				} else {
					disabledListRating = "";
					for(int i = 0; i < lstRat.size(); i++) {
						Rating rat = lstRat.get(i);
						lstRating.add(new SelectItem(rat.getId(), rat.getDescription()));
					}
				}
				disabled = "";
				if(params.get("j_idt10:rating") != null) {
					idRating = Integer.parseInt(params.get("j_idt10:rating"));
				}
				if(idRating > 0) {
					if(params.get("j_idt10:stakeHome") != null) 
						stakeHome = Float.parseFloat(params.get("j_idt10:stakeHome"));
					else
						stakeHome = 0;
					if(params.get("j_idt10:stakeAway") != null) 
						stakeAway = Float.parseFloat(params.get("j_idt10:stakeAway"));
					else
						stakeAway = 0;
					if(params.get("j_idt10:stakeTotal") != null) 
						stakeTotal = Float.parseFloat(params.get("j_idt10:stakeTotal"));
					else
						stakeTotal = 0;
					rating = sf.getRating(idRating);
					disabledRating = "";
					lstStatsRating = mf.getStatsRating(idMatch, idRating, stakeHome, stakeAway, stakeTotal);
					
				}
			} else {
				disabled = "display:none;";
			}

		}
		
	}
	
	public List<Stats> getStats() throws Exception {		
		return this.lstStats;
	}
	
	public List<StatsRating> getStatsRating() throws Exception {		
		return this.lstStatsRating;
	}
	
	public List<StatsRatingMatch> getStatsRatingMatchHome() throws Exception {
		if(this.lstStatsRating!=null)
			return this.lstStatsRating.get(0).getLstStats();
		else 
			return new ArrayList<StatsRatingMatch>();
	}
	
	public List<StatsRatingMatch> getStatsRatingMatchAway() throws Exception {
		if(this.lstStatsRating!=null)
			return this.lstStatsRating.get(1).getLstStats();
		else 
			return new ArrayList<StatsRatingMatch>();
	}
	
}
