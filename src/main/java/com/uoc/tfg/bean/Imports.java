package com.uoc.tfg.bean;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.uoc.tfg.business.MatchFacadeBean;

@ManagedBean(name="imports")
@RequestScoped
public class Imports {
	public String initdate;
	public String finishdate;
	public String today;
	
	public Imports() throws Exception {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		MatchFacadeBean mf = new MatchFacadeBean();
		Date yesterday = mf.getLastDateMatch();
		if(yesterday!=null) {
			Calendar calendar = Calendar.getInstance();
	        calendar.setTime(yesterday); // Configuramos la fecha que se recibe
	        calendar.add(Calendar.DAY_OF_YEAR, 1);
			Date t = new Date(calendar.getTimeInMillis());
			this.today = df.format(t);
			this.initdate = this.getToday();
			calendar.add(Calendar.MONTH, 1);
			this.finishdate = df.format(new java.sql.Date(calendar.getTimeInMillis()));
		}
		
	}

	public String getInitdate() {
		return initdate;
	}
	public void setInitdate(String initdate) {
		this.initdate = initdate;
	}
	public String getFinishdate() {
		return finishdate;
	}
	public void setFinishdate(String finishdate) {
		this.finishdate = finishdate;
	}
	public String getToday() {
		return today;
	}
	public void setToday(String today) {
		this.today = today;
	}

	public String initial() throws Exception {
		
		MatchFacadeBean mf = new MatchFacadeBean();
		
		mf.getFullGameSchedule();
		
		return "/imports/importsView.xhtml?faces-redirect=true";
	}
	
	public String importdates(String ini, String end) throws Exception {
		java.util.Date utilDate = new SimpleDateFormat("dd/MM/yyyy").parse(ini);
		Date dini = new java.sql.Date(utilDate.getTime());
		utilDate = new SimpleDateFormat("dd/MM/yyyy").parse(end);
		Date dend = new java.sql.Date(utilDate.getTime());
		MatchFacadeBean mf = new MatchFacadeBean();
		mf.getPastGamelogs(dini, dend);
		
		return "/imports/importsView.xhtml?faces-redirect=true";
	}
	
	public String importdate(String date) throws Exception {
		java.util.Date utilDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
		Date d = new java.sql.Date(utilDate.getTime());
		
		MatchFacadeBean mf = new MatchFacadeBean();
		mf.getDayGamelogs(d);
		
		return "/imports/importsView.xhtml?faces-redirect=true";
	}
}
