package com.showing.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class ShowingVO implements Serializable {

	private Integer showingId;
	private Integer theaterId;
	private Integer movieId;
	private Date showingDate;
	private Time showingTime;
	
	public ShowingVO() {
		super();

	}
	public ShowingVO(Integer showingId, Integer theaterId, Integer movieId, Date showingDate, Time showingTime) {
		super();
		this.showingId = showingId;
		this.theaterId = theaterId;
		this.movieId = movieId;
		this.showingDate = showingDate;
		this.showingTime = showingTime;
	}
	public Integer getShowingId() {
		return showingId;
	}
	public void setShowingId(Integer showingId) {
		this.showingId = showingId;
	}
	public Integer getTheaterId() {
		return theaterId;
	}
	public void setTheaterId(Integer theaterId) {
		this.theaterId = theaterId;
	}
	public Integer getMovieId() {
		return movieId;
	}
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}
	public Date getShowingDate() {
		return showingDate;
	}
	public void setShowingDate(Date showingDate) {
		this.showingDate = showingDate;
	}
	public Time getShowingTime() {
		return showingTime;
	}
	public void setShowingTime(Time showingTime) {
		this.showingTime = showingTime;
	}
	
	
}
